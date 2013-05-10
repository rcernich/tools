/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
package org.switchyard.tools.ui.editor.diagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.Graphics;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.BorderDecorator;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextMenuEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.IImageDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.eclipse.soa.sca.sca1_1.model.sca.Interface;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.switchyard.tools.models.switchyard1_0.hornetq.BindingType;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPBindingType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardBindingType;
import org.switchyard.tools.ui.editor.BindingTypeExtensionManager;
import org.switchyard.tools.ui.editor.ComponentTypeExtensionManager;
import org.switchyard.tools.ui.editor.ImageProvider;
import org.switchyard.tools.ui.editor.diagram.binding.CreateBindingFeature;
import org.switchyard.tools.ui.editor.diagram.component.AbstractComponentFactory;
import org.switchyard.tools.ui.editor.diagram.component.CreateComponentFeature;
import org.switchyard.tools.ui.editor.diagram.componentreference.SCADiagramCreateComponentReferenceFeature;
import org.switchyard.tools.ui.editor.diagram.componentreference.SCADiagramCustomPromoteReferenceFeature;
import org.switchyard.tools.ui.editor.diagram.componentservice.SCADiagramCreateComponentServiceFeature;
import org.switchyard.tools.ui.editor.diagram.compositereference.SCADiagramCreateCompositeReferenceFeature;
import org.switchyard.tools.ui.editor.diagram.connections.SCADiagramCreateComponentServiceLinkFeature;
import org.switchyard.tools.ui.editor.diagram.connections.SCADiagramCreateReferenceLinkFeature;
import org.switchyard.tools.ui.editor.diagram.service.SCADiagramCreateServiceFeature;
import org.switchyard.tools.ui.editor.diagram.service.SCADiagramCustomPromoteServiceFeature;
import org.switchyard.tools.ui.editor.diagram.shared.CompositeCreateConnectionFeature;
import org.switchyard.tools.ui.editor.property.adapters.LabelAdapter;
import org.switchyard.tools.ui.validation.ValidationStatusAdapter;

/**
 * @author bfitzpat
 * 
 */
public class SCADiagramToolBehaviorProvider extends DefaultToolBehaviorProvider {

    /**
     * @param diagramTypeProvider the diagram type provider
     */
    public SCADiagramToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
        super(diagramTypeProvider);
    }

    private ContextMenuEntry addCreateFeatureAContextMenu(ICreateFeature feature, ICustomContext context, List<IContextMenuEntry> menuList) {
        CreateWrapperCustomFeature wrapper = new CreateWrapperCustomFeature(getFeatureProvider(), feature);
        ContextMenuEntry newMenu = new ContextMenuEntry(wrapper, context);
        newMenu.setSubmenu(false);
        if (newMenu.canExecute()) {
            menuList.add(newMenu);
        }
        return newMenu;
    }
    
    private IContextMenuEntry addCreateFeatureAsSubMenu(ICreateFeature feature, ICustomContext context, IContextMenuEntry parent) {
        CreateWrapperCustomFeature wrapper = new CreateWrapperCustomFeature(getFeatureProvider(), feature);
        ContextMenuEntry newMenu = new ContextMenuEntry(wrapper, context);
        newMenu.setSubmenu(true);
        if (newMenu.canExecute()) {
            parent.add(newMenu);
        }
        return newMenu;
    }

    /**
     * @see org.eclipse.graphiti.tb.DefaultToolBehaviorProvider#getContextMenu(org.eclipse.graphiti.features.context.ICustomContext)
     * @param context incoming context
     * @return menu entries
     */
    public IContextMenuEntry[] getContextMenu(ICustomContext context) {
        List<IContextMenuEntry> menuList = new ArrayList<IContextMenuEntry>();
        if (context.getPictogramElements() != null) {
            Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(context.getPictogramElements()[0]);
            if (bo != null) {

                if (bo instanceof Component || bo instanceof Contract) {
                    ContextMenuEntry openMenu = new ContextMenuEntry(new SCADiagramOpenOnDoubleClickFeature(
                            getFeatureProvider()), context);
                    openMenu.setText("Open");
                    openMenu.setSubmenu(false);
                    menuList.add(openMenu);
                }
                if (bo instanceof Component) {
                    ContextMenuEntry addButton =
                            addCreateFeatureAContextMenu(null, context, menuList);
                    addButton.setText("Add...");
                    addButton.setSubmenu(true);

                    SCADiagramCreateComponentServiceFeature addService = new SCADiagramCreateComponentServiceFeature(getFeatureProvider());
                    addCreateFeatureAsSubMenu(addService, context, addButton);

                    SCADiagramCreateComponentReferenceFeature addComponentReference = new SCADiagramCreateComponentReferenceFeature(getFeatureProvider());
                    addCreateFeatureAsSubMenu(addComponentReference, context, addButton);
                    
                    IContextMenuEntry addComponentButton =
                            addCreateFeatureAsSubMenu(null, context, addButton);
                    addComponentButton.setText("Implementation...");
                    addComponentButton.setIconId(ImageProvider.IMG_16_COMPONENT);
                    
                    for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateComponentFeatures()) {
                        addCreateFeatureAsSubMenu(cf, context, addComponentButton);
                    }
                }
                if (bo instanceof Contract) {
                    if (!(((Contract) bo).eContainer() instanceof Component)) {
                        ContextMenuEntry addButton =
                                addCreateFeatureAContextMenu(null, context, menuList);
                        addButton.setText("Add Binding...");
                        addButton.setIconId(ImageProvider.IMG_16_CHAIN);
                        addButton.setSubmenu(true);

                        for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateBindingFeatures()) {
                            addCreateFeatureAsSubMenu(cf, context, addButton);
                        }
                    }

                    ContextMenuEntry openMenu = new ContextMenuEntry(new ChangeInterfaceCustomFeature(
                            getFeatureProvider()), context);
                    openMenu.setSubmenu(false);
                    menuList.add(openMenu);
                }
                if (bo instanceof Composite) {
                    ContextMenuEntry addButton =
                            addCreateFeatureAContextMenu(null, context, menuList);
                    addButton.setText("Add...");
                    addButton.setSubmenu(true);

                    SCADiagramCreateServiceFeature addService = new SCADiagramCreateServiceFeature(getFeatureProvider());
                    addCreateFeatureAsSubMenu(addService, context, addButton);

                    SCADiagramCreateCompositeReferenceFeature addCompositeReference = new SCADiagramCreateCompositeReferenceFeature(getFeatureProvider());
                    addCreateFeatureAsSubMenu(addCompositeReference, context, addButton);
                    
                    IContextMenuEntry addComponentButton =
                            addCreateFeatureAsSubMenu(null, context, addButton);
                    addComponentButton.setText("Component...");
                    addComponentButton.setIconId(ImageProvider.IMG_16_COMPONENT);
                    
                    CreateComponentFeature addComponent2 = new CreateComponentFeature(getFeatureProvider(), 
                            new AbstractComponentFactory(), "Component",
                            "Create a simple component with no implementation, services or references.");

                    addCreateFeatureAsSubMenu(addComponent2, context, addComponentButton);

                    for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateComponentFeatures()) {
                        addCreateFeatureAsSubMenu(cf, context, addComponentButton);
                    }
                }
            }
        }
        menuList.addAll(Arrays.asList(super.getContextMenu(context)));
        return menuList.toArray(new IContextMenuEntry[menuList.size()]);
    }

    /**
     * @param pe incoming pictogram element
     * @return point with x/y
     */
    public Location getDecoratorLocationUpperRight(PictogramElement pe) {
        GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
        int x = 4;
        int y = 4;
        EList<GraphicsAlgorithm> gaChildren = ga.getGraphicsAlgorithmChildren();
        for (Object gaChild : gaChildren.toArray()) {
            if (gaChild instanceof Polygon) {
                Polygon poly = (Polygon) gaChild;
                EList<Point> points = poly.getPoints();
                for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
                    Point point = (Point) iterator.next();
                    if (point.getY() < y && point.getX() > x) {
                        x = point.getX();
                    }
                }
                break;
            }

        }
        return new Location(x, y);
    }

    /**
     * @param pe incoming pictogram element
     * @return point with x/y
     */
    public Location getDecoratorLocationLowerLeft(PictogramElement pe) {
        GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
        int x = 4;
        int y = 4;
        EList<GraphicsAlgorithm> gaChildren = ga.getGraphicsAlgorithmChildren();
        for (Object gaChild : gaChildren.toArray()) {
            if (gaChild instanceof Polygon) {
                Polygon poly = (Polygon) gaChild;
                EList<Point> points = poly.getPoints();
                for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
                    Point point = (Point) iterator.next();
                    if (point.getY() > y) {
                        y = point.getY();
                    }
                }
                break;
            } else if (gaChild instanceof RoundedRectangle) {
                RoundedRectangle rect = (RoundedRectangle) gaChild;
                int rectY = rect.getY();
                if (rectY > y) {
                    y = rectY;
                }
            }
        }
        return new Location(x, y);
    }

    /**
     * @param pe incoming pictogram element
     * @return point with x/y
     */
    public Location getDecoratorLocationTopLeft(PictogramElement pe) {
        GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
        int x = 30;
        int y = 30;
        EList<GraphicsAlgorithm> gaChildren = ga.getGraphicsAlgorithmChildren();
        for (Object gaChild : gaChildren.toArray()) {
            if (gaChild instanceof Polygon) {
                Polygon poly = (Polygon) gaChild;
                EList<Point> points = poly.getPoints();
                for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
                    Point point = (Point) iterator.next();
                    if (point.getY() < y) {
                        y = point.getY();
                    }
                    if (point.getX() < x) {
                        x = point.getX();
                    }
                }
                break;
            } else if (gaChild instanceof RoundedRectangle) {
                RoundedRectangle rect = (RoundedRectangle) gaChild;
                int rectX = rect.getX();
                int rectY = rect.getY();
                if (rectY < y) {
                    y = rectY;
                }
                if (rectX < x) {
                    x = rectX;
                }
            }
        }
        return new Location(x, y);
    }

    @Override
    public IDecorator[] getDecorators(PictogramElement pe) {
        if (pe instanceof Anchor) {
            // no decorators for anchors
            return super.getDecorators(pe);
        } else if (pe instanceof ConnectionDecorator) {
            return getDecoratorsForConnection((ConnectionDecorator) pe);
        }
        IFeatureProvider featureProvider = getFeatureProvider();
        Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
        if (bo == null || !(bo instanceof EObject)) {
            return super.getDecorators(pe);
        }
        boolean needsOffset = false;
        List<IDecorator> decorators = new ArrayList<IDecorator>();
        if (bo instanceof Service || bo instanceof Reference) {
            needsOffset = true;
            decorators.addAll(addDecoratorsForCompositeContract((Contract) bo, pe));
        } else if (bo instanceof Component) {
            Component component = (Component) bo;
            if (component.getImplementation() != null) {
                Implementation implementation = component.getImplementation();
                String text = LabelAdapter.getLabel(implementation);
                IImageDecorator imageRenderingDecorator = ComponentTypeExtensionManager.instance()
                        .getExtensionFor(implementation.getClass()).getImageDecorator(implementation);
                if (imageRenderingDecorator == null) {
                    imageRenderingDecorator = new ImageDecorator(ImageProvider.IMG_16_UNKNOWN_IMPL);
                }
                imageRenderingDecorator.setMessage("Implementation: " + text);
                Location loc = getDecoratorLocationTopLeft(pe);
                imageRenderingDecorator.setX(loc.getX() + 10);
                imageRenderingDecorator.setY(loc.getY() + 4);
                decorators.add(imageRenderingDecorator);
            }
        }

        ValidationStatusAdapter statusAdapter = (ValidationStatusAdapter) EcoreUtil.getRegisteredAdapter((EObject) bo,
                ValidationStatusAdapter.class);
        if (statusAdapter != null) {
            final IImageDecorator decorator = createDecorator(statusAdapter.getValidationStatus());
            if (decorator != null) {
                GraphicsAlgorithm ga = getSelectionBorder(pe);
                if (ga == null) {
                    ga = pe.getGraphicsAlgorithm();
                }
                if (bo instanceof Composite) {
                    decorator.setX(ga.getX()+5);
                    decorator.setY(ga.getY()+5);
                } else {
                    decorator.setX(ga.getWidth() - 10 - (needsOffset ? 28 : 0));
                    decorator.setY(ga.getHeight() - 10 - (needsOffset ? 8 : 0));
                }
                decorators.add(decorator);
            }
        }

        if (getDiagramTypeProvider().getDiagramBehavior().getEditingDomain().isReadOnly(((EObject) bo).eResource())) {
            decorators.add(new BorderDecorator(IColorConstant.GRAY, null, Graphics.LINE_DASH));
        }
        return decorators.toArray(new IDecorator[decorators.size()]);
    }

    private List<IDecorator> addDecoratorsForCompositeContract(Contract contract, PictogramElement pe) {
        final List<IDecorator> decorators = new ArrayList<IDecorator>();
        if (!contract.getBinding().isEmpty()) {
            EList<Binding> bindings = contract.getBinding();
            int x = 15;
            for (Binding binding : bindings) {
                IImageDecorator imageRenderingDecorator = BindingTypeExtensionManager.instance()
                        .getExtensionFor(binding.getClass()).getImageDecorator(binding);
                imageRenderingDecorator.setX(x);
                x += 20;
                String text = LabelAdapter.getLabel(binding);
                if (binding instanceof SwitchYardBindingType) {
                    text = "Binding: " + LabelAdapter.getLabel(binding);
                }
                if (binding instanceof SOAPBindingType) {
                    SOAPBindingType soapBinding = (SOAPBindingType) binding;
                    text = text + "\n" + soapBinding.getWsdl();
                } else if (binding instanceof BindingType) {
                    BindingType hornetQBinding = (BindingType) binding;
                    text = text + "\n" + hornetQBinding.getUri();
                }
                imageRenderingDecorator.setMessage(text);
                decorators.add(imageRenderingDecorator);
            }
        }
        if (contract.getInterface() != null) {
            ImageDecorator imageRenderingDecorator = LabelAdapter
                    .getImageDecoratorForInterface(contract.getInterface());
            Interface intfc = contract.getInterface();
            String text = LabelAdapter.getLabel(intfc);
            imageRenderingDecorator.setMessage("Interface: " + text);
            Location loc = getDecoratorLocationLowerLeft(pe);
            imageRenderingDecorator.setY(loc.getY() - 18);
            imageRenderingDecorator.setX(15);
            decorators.add(imageRenderingDecorator);
        } else {
            Interface intfc = null;
            if (contract instanceof Service && ((Service) contract).getPromote() != null) {
                intfc = ((Service) contract).getPromote().getInterface();
            } else if (contract instanceof Reference) {
                for (Contract promoted : ((Reference) contract).getPromote()) {
                    if (promoted != null && promoted.getInterface() != null) {
                        intfc = promoted.getInterface();
                        break;
                    }
                }
            }
            if (intfc != null) {
                ImageDecorator imageRenderingDecorator = LabelAdapter.getImageDecoratorForInterface(intfc);
                // ImageDecorator imageRenderingDecorator = new
                // ImageDecorator(ImageProvider.IMG_16_INTERFACE);
                String text = LabelAdapter.getLabel(intfc);
                Location loc = getDecoratorLocationLowerLeft(pe);
                imageRenderingDecorator.setY(loc.getY() - 18);
                imageRenderingDecorator.setMessage("Interface (Inherited): " + text);
                decorators.add(imageRenderingDecorator);
            }
        }
        return decorators;
    }

    private IDecorator[] getDecoratorsForConnection(ConnectionDecorator pe) {
        if (pe.getConnection() == null || pe.getConnection().getStart() == null || pe.getConnection().getEnd() == null) {
            return super.getDecorators(pe);
        }
        Object sourceBO = getFeatureProvider().getBusinessObjectForPictogramElement(pe.getConnection().getStart());
        Object targetBO = getFeatureProvider().getBusinessObjectForPictogramElement(pe.getConnection().getEnd());
        if (sourceBO == null || targetBO == null) {
            return super.getDecorators(pe);
        }
        ValidationStatusAdapter statusAdapter = (ValidationStatusAdapter) EcoreUtil.getRegisteredAdapter(
                (EObject) sourceBO, ValidationStatusAdapter.class);
        if (statusAdapter == null) {
            return super.getDecorators(pe);
        }
        final IImageDecorator decorator = createDecorator(statusAdapter.getConnectionStatus((EObject) targetBO));
        if (decorator != null) {
            decorator.setX(0);
            decorator.setY(0);
            return new IDecorator[] {decorator };
        }
        return super.getDecorators(pe);
    }

    private IImageDecorator createDecorator(IStatus status) {
        final IImageDecorator decorator;
        switch (status.getSeverity()) {
        case IStatus.INFO:
            decorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_INFORMATION_TSK);
            break;
        case IStatus.WARNING:
            decorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK);
            break;
        case IStatus.ERROR:
            decorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_ERROR_TSK);
            break;
        default:
            decorator = null;
            break;
        }
        if (decorator != null) {
            decorator.setMessage(status.getMessage());
        }
        return decorator;
    }

    @Override
    public String getToolTip(GraphicsAlgorithm ga) {
        PictogramElement pe = ga.getPictogramElement();
        Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
        if (bo instanceof Composite) {
            String name = ((Composite) bo).getName();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        } else if (bo instanceof Component) {
            String name = ((Component) bo).getName();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        } else if (bo instanceof Service) {
            String name = ((Service) bo).getName();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        } else if (bo instanceof ComponentService) {
            String name = ((ComponentService) bo).getName();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        } else if (bo instanceof ComponentReference) {
            String name = ((ComponentReference) bo).getName();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        } else if (bo instanceof Reference) {
            String name = ((Reference) bo).getName();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        }
        return null;
    }

    @Override
    public IPaletteCompartmentEntry[] getPalette() {
        List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();

        // add new compartment for generic entries
        PaletteCompartmentEntry compositeEntry = new PaletteCompartmentEntry("Generic", null);
        for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateGenericFeatures()) {
            compositeEntry.addToolEntry(new ObjectCreationToolEntry(cf.getCreateName(), cf.getCreateDescription(), cf
                    .getCreateImageId(), cf.getCreateLargeImageId(), cf));
        }
        for (ICreateConnectionFeature cf : getFeatureProvider().getCreateConnectionFeatures()) {
            ConnectionCreationToolEntry connectionCreationToolEntry = new ConnectionCreationToolEntry(
                    cf.getCreateName(), cf.getCreateDescription(), cf.getCreateImageId(), cf.getCreateLargeImageId());
            connectionCreationToolEntry.addCreateConnectionFeature(cf);
            compositeEntry.addToolEntry(connectionCreationToolEntry);
        }
        ret.add(compositeEntry);

        // add new compartment for component/implementation types
        PaletteCompartmentEntry componentEntry = new PaletteCompartmentEntry("Implementations", null);
        for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateComponentFeatures()) {
            componentEntry.addToolEntry(new ObjectCreationToolEntry(cf.getCreateName(), cf.getCreateDescription(), cf
                    .getCreateImageId(), cf.getCreateLargeImageId(), cf));
        }
        ret.add(componentEntry);

        // add new compartment for bindings
        PaletteCompartmentEntry bindingsEntry = new PaletteCompartmentEntry("Bindings", null);
        for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateBindingFeatures()) {
            bindingsEntry.addToolEntry(new ObjectCreationToolEntry(cf.getCreateName(), cf.getCreateDescription(), cf
                    .getCreateImageId(), cf.getCreateLargeImageId(), cf));
        }
        ret.add(bindingsEntry);

        // add new compartment for anything else
        PaletteCompartmentEntry miscEntry = new PaletteCompartmentEntry("Other", null);
        for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateOtherFeatures()) {
            miscEntry.addToolEntry(new ObjectCreationToolEntry(cf.getCreateName(), cf.getCreateDescription(), cf
                    .getCreateImageId(), cf.getCreateLargeImageId(), cf));
        }
        if (!miscEntry.getToolEntries().isEmpty()) {
            ret.add(miscEntry);
        }

        return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
    }

    private ContextButtonEntry addCreateFeatureAsButtonToPad(ICreateFeature feature, ICreateContext context, IContextButtonPadData data) {
        ContextButtonEntry newButton = new ContextButtonEntry(feature, context);
        newButton.setIconId(feature.getCreateImageId());
        newButton.setText(feature.getCreateName());
        newButton.setDescription(feature.getCreateDescription());
        if (newButton.canExecute()) {
            data.getDomainSpecificContextButtons().add(newButton);
        }
        return newButton;
    }
    
    private ContextButtonEntry addCreateFeatureAsContextButtonToPad(ICreateFeature feature, ICreateContext context, ContextButtonEntry parent) {
        ContextButtonEntry newButton = new ContextButtonEntry(feature, context);
        newButton.setIconId(feature.getCreateImageId());
        newButton.setText(feature.getCreateName());
        newButton.setDescription(feature.getCreateDescription());
        if (newButton.canExecute()) {
            parent.addContextButtonMenuEntry(newButton);
        }
        return newButton;
    }
    
    private ICreateContext adaptCustomContextToCreateContext(ICustomContext customContext) {
        CreateContext createContext = new CreateContext();
        PictogramElement[] picElements = customContext.getPictogramElements();
        if (picElements[0] instanceof ContainerShape) {
            createContext.setTargetContainer((ContainerShape) picElements[0]);

            // making the absolute location point relative to the container
            int x = createContext.getTargetContainer().getGraphicsAlgorithm().getX() + 20;
            int y = createContext.getTargetContainer().getGraphicsAlgorithm().getY() + 20;
            createContext.setLocation(x, y);
        }
        
        return createContext;
    }

    /* (non-Javadoc)
     * @see org.eclipse.graphiti.tb.DefaultToolBehaviorProvider#getContextButtonPad(org.eclipse.graphiti.features.context.IPictogramElementContext)
     */
    @Override
    public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {

        IContextButtonPadData data = super.getContextButtonPad(context);
        PictogramElement pe = context.getPictogramElement();
        Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);

        ICustomContext customContext = new CustomContext(
                new PictogramElement[] {context.getPictogramElement() });
        
        // create a create context for when we need it
        ICreateContext createContext = adaptCustomContextToCreateContext(customContext);
        
        data.getDomainSpecificContextButtons().add(
                new ContextButtonEntry(new PropertiesDialogFeature(getFeatureProvider()),
                        new CustomContext(new PictogramElement[] {context.getPictogramElement() })));

        if (bo instanceof Composite) {
            // just update, no delete
            setGenericContextButtons(data, pe, CONTEXT_BUTTON_UPDATE);

            // show synchronize on composite
            data.getDomainSpecificContextButtons().add(
                    new ContextButtonEntry(new SynchronizeGeneratedModelFeature(getFeatureProvider()),
                            new CustomContext(new PictogramElement[] {context.getPictogramElement() })));
            
            AutoLayoutFeature autoLayout = new AutoLayoutFeature(getFeatureProvider());
            ContextButtonEntry autoLayoutButton = new ContextButtonEntry(autoLayout, customContext);
            data.getDomainSpecificContextButtons().add(autoLayoutButton);

            SCADiagramCreateServiceFeature addService = new SCADiagramCreateServiceFeature(getFeatureProvider());
            addCreateFeatureAsButtonToPad(addService, createContext, data);

            SCADiagramCreateCompositeReferenceFeature addCompositeReference = new SCADiagramCreateCompositeReferenceFeature(getFeatureProvider());
            addCreateFeatureAsButtonToPad(addCompositeReference, createContext, data);
            
            CreateComponentFeature addComponent = new CreateComponentFeature(getFeatureProvider(), 
                    new AbstractComponentFactory(), "Component",
                    "Create a new component...");
            ContextButtonEntry addComponentButton =
                    addCreateFeatureAsButtonToPad(addComponent, createContext, data);
            
            CreateComponentFeature addComponent2 = new CreateComponentFeature(getFeatureProvider(), 
                    new AbstractComponentFactory(), "Component",
                    "Create a simple component with no implementation, services or references.");
            addCreateFeatureAsContextButtonToPad(addComponent2, createContext, addComponentButton);

            for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateComponentFeatures()) {
                addCreateFeatureAsContextButtonToPad(cf, createContext, addComponentButton);
            }
        } else {
            if (bo instanceof ComponentService) {
                SCADiagramCustomPromoteServiceFeature promote = new SCADiagramCustomPromoteServiceFeature(getFeatureProvider());
                ContextButtonEntry button = new ContextButtonEntry(promote, customContext);
                data.getDomainSpecificContextButtons().add(button);
                data.getPadLocation().setHeight(40);
                data.getPadLocation().setWidth(data.getPadLocation().getWidth() + 10);
                
                CreateServiceTestCustomFeature test = new CreateServiceTestCustomFeature(getFeatureProvider());
                ContextButtonEntry testButton = new ContextButtonEntry(test, customContext);
                data.getDomainSpecificContextButtons().add(testButton);

            } else if (bo instanceof ComponentReference) {
                SCADiagramCustomPromoteReferenceFeature promote = new SCADiagramCustomPromoteReferenceFeature(getFeatureProvider());
                ContextButtonEntry button = new ContextButtonEntry(promote, customContext);
                data.getDomainSpecificContextButtons().add(button);
                data.getPadLocation().setHeight(40);
                data.getPadLocation().setWidth(data.getPadLocation().getWidth() + 10);

            } else if (bo instanceof Component) {
                SCADiagramCreateComponentServiceFeature addComponentService = new SCADiagramCreateComponentServiceFeature(getFeatureProvider());
                addCreateFeatureAsButtonToPad(addComponentService, createContext, data);

                SCADiagramCreateComponentReferenceFeature addComponentReference = new SCADiagramCreateComponentReferenceFeature(getFeatureProvider());
                addCreateFeatureAsButtonToPad(addComponentReference, createContext, data);
                
                CreateComponentFeature addComponent = new CreateComponentFeature(getFeatureProvider(), 
                        new AbstractComponentFactory(), "Implementation",
                        "Create a new implementation...");
                ContextButtonEntry addComponentButton =
                        addCreateFeatureAsButtonToPad(addComponent, createContext, data);
                
                for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateComponentFeatures()) {
                    addCreateFeatureAsContextButtonToPad(cf, createContext, addComponentButton);
                }
            }
            if (bo instanceof Contract) {
                ChangeInterfaceCustomFeature intfChangeFeature = new ChangeInterfaceCustomFeature(getFeatureProvider());
                ContextButtonEntry button = new ContextButtonEntry(intfChangeFeature, customContext);
                data.getDomainSpecificContextButtons().add(button);

                if (!(((Contract) bo).eContainer() instanceof Component)) {
                    CreateBindingFeature addBinding = new CreateBindingFeature(getFeatureProvider(), 
                            null, "Binding",
                            "Create a new binding...");
                    ContextButtonEntry addBindingButton =
                            addCreateFeatureAsButtonToPad(addBinding, createContext, data);
                    
                    for (ICreateFeature cf : ((SCADiagramFeatureProvider) getFeatureProvider()).getCreateBindingFeatures()) {
                        addCreateFeatureAsContextButtonToPad(cf, createContext, addBindingButton);
                    }
                }
                
                CreateConnectionContext ccc = new CreateConnectionContext();
                ccc.setSourcePictogramElement(pe);
                Anchor anchor = null;
                if (pe instanceof Anchor) {
                    anchor = (Anchor) pe;
                } else if (pe instanceof AnchorContainer) {
                    // assume, that our shapes always have chopbox anchors
                    anchor = Graphiti.getPeService()
                             .getChopboxAnchor((AnchorContainer) pe);
                }
                ccc.setSourceAnchor(anchor);
                
                ContextButtonEntry linkbutton = new ContextButtonEntry(null, context);
                CompositeCreateConnectionFeature linkFeature = new CompositeCreateConnectionFeature(getFeatureProvider(), 
                        "Promote",
                        "Promote a composite service/reference", 
                        new SCADiagramCreateReferenceLinkFeature(getFeatureProvider()),
                        new SCADiagramCreateComponentServiceLinkFeature(getFeatureProvider()));
                linkbutton.setText(linkFeature.getCreateName());
                linkbutton.setDescription(linkFeature.getCreateDescription());
                linkbutton.setIconId(linkFeature.getCreateImageId());
                if (linkFeature.isAvailable(ccc) && linkFeature.canStartConnection(ccc)) {
                    linkbutton.addDragAndDropFeature(linkFeature);
                }
                if (linkbutton.getDragAndDropFeatures().size() > 0) {
                    data.getDomainSpecificContextButtons().add(linkbutton);
                }

                Java2WSDLCustomFeature java2WSDL = new Java2WSDLCustomFeature(getFeatureProvider());
                if (java2WSDL.canExecute(customContext)) {
                    data.getDomainSpecificContextButtons().add(new ContextButtonEntry(java2WSDL, customContext));
                } else {
                    WSDL2JavaCustomFeature wsdl2Java = new WSDL2JavaCustomFeature(getFeatureProvider());
                    if (wsdl2Java.canExecute(customContext)) {
                        data.getDomainSpecificContextButtons().add(new ContextButtonEntry(wsdl2Java, customContext));
                    }
                }
            }
            setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE);
        }
        
        return data;
    }

    @Override
    public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
        if (context.getPictogramElements() != null) {
            PictogramElement[] elements = context.getPictogramElements();
            if (elements.length > 0) {
                PictogramElement firstOne = elements[0];
                Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(firstOne);
                if (bo instanceof Component || bo instanceof Service || bo instanceof Reference
                        || bo instanceof ComponentService || bo instanceof ComponentReference) {
                    return new SCADiagramOpenOnDoubleClickFeature(getFeatureProvider());
                }
            }
        }
        return super.getDoubleClickFeature(context);
    }

    @Override
    public GraphicsAlgorithm getSelectionBorder(PictogramElement pe) {
        GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
        if (!ga.getFilled()) {
            for (GraphicsAlgorithm childGA : ga.getGraphicsAlgorithmChildren()) {
                if (childGA.getFilled()) {
                    return childGA;
                }
            }
        }
        return super.getSelectionBorder(pe);
    }

    @Override
    public GraphicsAlgorithm[] getClickArea(PictogramElement pe) {
        GraphicsAlgorithm ga = getSelectionBorder(pe);
        if (ga == null) {
            return super.getClickArea(pe);
        }
        return new GraphicsAlgorithm[] {ga };
    }

    @Override
    public boolean equalsBusinessObjects(Object o1, Object o2) {
        // we don't want to use EcoreUtil.equals() as the parent does.
        // null check
        if (o1 == null) {
            return o2 == null;
        } else if (o2 == null) {
            return false;
        }

        if (o1 instanceof EObject && o2 instanceof EObject) {
            EObject eo1 = (EObject) o1;
            EObject eo2 = (EObject) o2;
            // proxy check
            if (eo1.eIsProxy()) {
                return eo2.eIsProxy()
                        && ((InternalEObject) eo1).eProxyURI().equals(((InternalEObject) eo2).eProxyURI());
            } else if (eo2.eIsProxy()) {
                return false;
            }
            return eo1 == eo2;
        }
        return false;
    }

    private class Location {
        private int _x = 0;
        private int _y = 0;

        /**
         * @param locx x coord
         * @param locy y coord
         */
        public Location(int locx, int locy) {
            _x = locx;
            _y = locy;
        }

        /**
         * @return x coord
         */
        public int getX() {
            return _x;
        }

        /**
         * @return y coord
         */
        public int getY() {
            return _y;
        }
    }

}
