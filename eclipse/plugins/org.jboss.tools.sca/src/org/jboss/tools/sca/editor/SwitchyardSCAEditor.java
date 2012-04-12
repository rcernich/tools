/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.jboss.tools.sca.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Lifecycle;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.internal.editor.GFPaletteRoot;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.jboss.tools.sca.Activator;
import org.jboss.tools.sca.core.ModelHandler;
import org.jboss.tools.sca.core.ModelHandlerLocator;
import org.jboss.tools.sca.diagram.di.DIImport;
import org.jboss.tools.switchyard.model.bean.BeanPackage;
import org.jboss.tools.switchyard.model.bpm.BPMPackage;
import org.jboss.tools.switchyard.model.clojure.ClojurePackage;
import org.jboss.tools.switchyard.model.commonrules.CommonRulesPackage;
import org.jboss.tools.switchyard.model.hornetq.HornetQPackage;
import org.jboss.tools.switchyard.model.rules.RulesPackage;
import org.jboss.tools.switchyard.model.soap.SOAPPackage;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;
import org.jboss.tools.switchyard.model.switchyard.util.SwitchyardResourceFactoryImpl;
import org.jboss.tools.switchyard.model.switchyard.util.SwitchyardResourceImpl;
import org.jboss.tools.switchyard.model.transform.TransformPackage;
import org.jboss.tools.switchyard.model.validate.ValidatePackage;
import org.open.oasis.docs.ns.opencsa.sca.bpel.BPELPackage;

/**
 * 
 */
@SuppressWarnings("restriction")
public class SwitchyardSCAEditor extends DiagramEditor {

	public static final String EDITOR_ID = "org.jboss.tools.sca.switchyard.editor";
	public static final String CONTRIBUTOR_ID = "org.jboss.tools.sca.diagram.PropertyContributor";

	private ModelHandler modelHandler;
	private URI modelUri;
	private URI diagramUri;

	private IFile modelFile;
	private IFile diagramFile;
	
	private IWorkbenchListener workbenchListener;
	private ISelectionListener selectionListener;
	private boolean workbenchShutdown = false;
	private static SwitchyardSCAEditor activeEditor;
	
	private SwitchyardSCAEditingDomainListener editingDomainListener;
	
//	private Bpmn2Preferences preferences;
//	private TargetRuntime targetRuntime;

	protected SwitchyardSCAEditorAdapter editorAdapter;

	protected class SwitchyardSCAEditorAdapter implements Adapter {
		public Notifier getTarget() { return null; }
		public void setTarget(Notifier newTarget) { }
		public boolean isAdapterForType(Object type) { return (type == SwitchyardSCAEditorAdapter.class); }
		public void notifyChanged(Notification notification) { }
		public SwitchyardSCAEditor getSwitchyardEditor() { return SwitchyardSCAEditor.this; }
	}

	/**
	 * Given a ResourceSet, this helper identifies the BPELEditor (if any) that created it
	 */
	public static SwitchyardSCAEditor getEditor(EObject object) {
		if (object!=null && object.eResource()!=null)
			return getEditor(object.eResource().getResourceSet());
		return null;
	}
	
	public static SwitchyardSCAEditor getActiveEditor() {
		return activeEditor;
	}
	
	private void setActiveEditor(SwitchyardSCAEditor editor) {
		activeEditor = editor;
//		if (activeEditor!=null) {
//			TargetRuntime.setActiveProject(modelFile.getProject());
//			TargetRuntime.setCurrentRuntime( getTargetRuntime() );
//		}
	}

	public static SwitchyardSCAEditor getEditor(ResourceSet resourceSet) {
	    Iterator<Adapter> it = resourceSet.eAdapters().iterator();
	    while (it.hasNext()) {
	        Object next = it.next();
	        if (next instanceof SwitchyardSCAEditorAdapter) {
	            return ((SwitchyardSCAEditorAdapter)next).getSwitchyardEditor();
	        }
	    }
	    return null;
	}
	
	protected SwitchyardSCAEditorAdapter getEditorAdapter() {
		return editorAdapter;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		
		try {
//			Bpmn2DiagramType diagramType = Bpmn2DiagramType.NONE;
			if (input instanceof IFileEditorInput) {
				modelFile = ((IFileEditorInput) input).getFile();
//				loadPreferences(modelFile.getProject());

				input = createNewDiagramEditorInput();

			} else if (input instanceof DiagramEditorInput) {
				getModelPathFromInput((DiagramEditorInput) input);
//				loadPreferences(modelFile.getProject());
				if (input instanceof SwitchyardSCADiagramEditorInput)
				// This was incorrectly constructed input, we ditch the old one and make a new and clean one instead
				// This code path comes in from the New File Wizard
				input = createNewDiagramEditorInput();
			}
		} catch (CoreException e) {
			Activator.showErrorWithLogging(e);
		}
		
		// add a listener so we get notified if the workbench is shutting down.
		// in this case we don't want to delete the temp file!
		addWorkbenchListener();
		setActiveEditor(this);
		
		super.init(site, input);
		addSelectionListener();
	}

	/**
	 * ID for tabbed property sheets.
	 * 
	 * @return the contributor id
	 */
	@Override
	public String getContributorId() {
		return CONTRIBUTOR_ID;
	}

	private void getModelPathFromInput(DiagramEditorInput input) {
		URI uri = input.getDiagram().eResource().getURI();
		String uriString = uri.trimFragment().toPlatformString(true);
		modelFile = SwitchyardSCADiagramCreator.getModelFile(new Path(uriString));
	}

	/**
	 * Beware, creates a new input and changes this editor!
	 */
	private SwitchyardSCADiagramEditorInput createNewDiagramEditorInput() throws CoreException {
		IPath fullPath = modelFile.getFullPath();
		modelUri = URI.createPlatformResourceURI(fullPath.toString(), true);

		IFolder folder = SwitchyardSCADiagramCreator.getTempFolder(fullPath);
		diagramFile = SwitchyardSCADiagramCreator.getTempFile(fullPath,folder);

		// Create new temporary diagram file
		SwitchyardSCADiagramCreator creator = new SwitchyardSCADiagramCreator();
		creator.setDiagramFile(diagramFile);

		SwitchyardSCADiagramEditorInput input = creator.createDiagram(false);
		diagramUri = creator.getUri();

		return input;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		modelHandler.save();
		((BasicCommandStack) getEditingDomain().getCommandStack()).saveIsDone();
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		
		// Hook a transaction exception handler so we can get diagnostics about EMF validation errors.
		getEditingDomainListener();
		
		BasicCommandStack basicCommandStack = (BasicCommandStack) getEditingDomain().getCommandStack();

		if (input instanceof DiagramEditorInput) {
			ResourceSet resourceSet = getEditingDomain().getResourceSet();

			ExtendedMetaData extendedMetaData = 
					new BasicExtendedMetaData(resourceSet.getPackageRegistry());
			getEditingDomain().getResourceSet().getLoadOptions().put
				(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
			getEditingDomain().getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put
				(Resource.Factory.Registry.DEFAULT_EXTENSION, 
						new SwitchyardResourceFactoryImpl());

			// Register the package to make it available during loading.
			resourceSet.getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200912", ScaPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put ("urn:switchyard-config:switchyard:1.0", SwitchyardPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-config:transform:1.0", TransformPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-bean:config:1.0", BeanPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-config:validate:1.0", ValidatePackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-soap:config:1.0", SOAPPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-rules:config:1.0", RulesPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-hornetq:config:1.0", HornetQPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-common-rules:config:1.0", CommonRulesPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-clojure:config:1.0", ClojurePackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("urn:switchyard-component-bpm:config:1.0", BPMPackage.eINSTANCE);
			resourceSet.getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200903", BPELPackage.eINSTANCE);

			SwitchyardResourceImpl switchyardResource = (SwitchyardResourceImpl) resourceSet.createResource(modelUri);

//			resourceSet.setURIConverter(new ProxyURIConverterImplExtension());
			resourceSet.eAdapters().add(editorAdapter = new SwitchyardSCAEditorAdapter());

			modelHandler = ModelHandlerLocator.createModelHandler(modelUri, switchyardResource);
			ModelHandlerLocator.put(diagramUri, modelHandler);

			try {
				if (modelFile.exists()) {
					switchyardResource.load(null);
				} else {
					doSave(null);
				}
			} catch (IOException e) {
				Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
				ErrorUtils.showErrorWithLogging(status);
			}
			basicCommandStack.execute(new RecordingCommand(getEditingDomain()) {

				@Override
				protected void doExecute() {
					importDiagram();
				}
			});
		}
		basicCommandStack.saveIsDone();
		basicCommandStack.flush();
	}
	
	private void importDiagram() {
		// make sure this guy is active, otherwise it's not selectable
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		IFeatureProvider featureProvider = getDiagramTypeProvider().getFeatureProvider();
		diagram.setActive(true);
		SwitchyardSCADiagramEditorInput input = (SwitchyardSCADiagramEditorInput) getEditorInput();
		if (input.getDiagram() != null) {
//			BPMNDiagram bpmnDiagram = modelHandler.createDiagramType(diagramType);
//			featureProvider.link(diagram, bpmnDiagram);
//			SwitchyardSCAEditor.this.doSave(null);
		}
		
		DIImport di = new DIImport();
		di.setDiagram(diagram);
		di.setDomain(getEditingDomain());
		di.setModelHandler(modelHandler);
		di.setFeatureProvider(featureProvider);
		di.generateFromDI();

		// this needs to happen AFTER the diagram has been imported because we need
		// to be able to determine the diagram type from the file's contents in order
		// to build the right tool palette for the target runtime and model enablements.
		GFPaletteRoot pr = (GFPaletteRoot)getPaletteRoot();
		pr.updatePaletteEntries();
	}

	@Override
	protected PictogramElement[] getPictogramElementsForSelection() {
		// filter out invisible elements when setting selection
		ArrayList<PictogramElement> visibleList = new ArrayList<PictogramElement>();
		PictogramElement[] pictogramElements = getSelectedPictogramElements();
		for (PictogramElement pe : pictogramElements) {
			if (pe.isVisible())
				visibleList.add(pe);
		}
		return visibleList.toArray(new PictogramElement[visibleList.size()]);
	}
	
	private void addWorkbenchListener() {
		if (workbenchListener==null) {
			workbenchListener = new IWorkbenchListener() {
				@Override
				public boolean preShutdown(IWorkbench workbench, boolean forced) {
					workbenchShutdown = true;
					return true;
				}

				@Override
				public void postShutdown(IWorkbench workbench) {
				}

			};
			PlatformUI.getWorkbench().addWorkbenchListener(workbenchListener);
		}
	}
	
	private void removeWorkbenchListener()
	{
		if (workbenchListener!=null) {
			PlatformUI.getWorkbench().removeWorkbenchListener(workbenchListener);
			workbenchListener = null;
		}
	}
	
	private void addSelectionListener()
	{
		if (selectionListener==null) {
			selectionListener = new ISelectionListener() {

				@Override
				public void selectionChanged(IWorkbenchPart part, ISelection selection) {
					if (part == SwitchyardSCAEditor.this) {
						setActiveEditor(SwitchyardSCAEditor.this );
					}
				}
				
			};
			getSite().getPage().addSelectionListener(selectionListener);
		}
	}

	private void removeSelectionListener()
	{
		if (selectionListener!=null) {
			getSite().getPage().removeSelectionListener(selectionListener);
		}
	}
	
	public SwitchyardSCAEditingDomainListener getEditingDomainListener() {
		if (editingDomainListener==null) {
			TransactionalEditingDomainImpl editingDomain = (TransactionalEditingDomainImpl)getEditingDomain();
			if (editingDomain==null) {
				return null;
			}
			editingDomainListener = new SwitchyardSCAEditingDomainListener(this);

			Lifecycle domainLifeCycle = (Lifecycle) editingDomain.getAdapter(Lifecycle.class);
			domainLifeCycle.addTransactionalEditingDomainListener(editingDomainListener);
		}
		return editingDomainListener;
	}
	
	public BasicDiagnostic getDiagnostics() {
		return getEditingDomainListener().getDiagnostics();
	}
	
	@Override
	public void dispose() {
		// clear ID mapping tables if no more instances of editor are active
		int instances = 0;
		IWorkbenchPage[] pages = getEditorSite().getWorkbenchWindow().getPages();
		for (IWorkbenchPage p : pages) {
			IEditorReference[] refs = p.getEditorReferences();
			instances += refs.length;
		}
//		ModelUtil.clearIDs(modelHandler.getResource(), instances==0);
		getResourceSet().eAdapters().remove(getEditorAdapter());
		removeSelectionListener();
		if (instances==0)
			setActiveEditor(null);
		
		super.dispose();
		ModelHandlerLocator.releaseModel(modelUri);
		// get rid of temp files and folders, button only if the workbench is being shut down.
		// when the workbench is restarted, we need to have those temp files around!
		if (!workbenchShutdown)
			SwitchyardSCADiagramCreator.dispose(diagramFile);
		removeWorkbenchListener();
//		getPreferences().dispose();
	}

	public IFile getModelFile() {
		return modelFile;
	}
	
	public ModelHandler getModelHandler() {
		return modelHandler;
	}
}
