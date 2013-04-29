/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.editor.diagram.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

/**
 * SwitchYardLayoutAlgorithm
 * 
 * <p/>
 * Layout algorithm used for laying out a SwitchYard diagram.
 */
public class SwitchYardLayoutAlgorithm extends GridLayoutAlgorithm {

    private List<Node> _roots = new ArrayList<Node>();
    private Map<Object, Node> _nodeMap = new HashMap<Object, Node>();

    /**
     * Create a new SwitchYardLayoutAlgorithm.
     */
    public SwitchYardLayoutAlgorithm() {
        this(LayoutStyles.NONE);
    }

    /**
     * Create a new SwitchYardLayoutAlgorithm.
     * 
     * @param styles styles to use.
     */
    public SwitchYardLayoutAlgorithm(int styles) {
        super(styles);
    }

    @Override
    protected void preLayoutAlgorithm(InternalNode[] entitiesToLayout, InternalRelationship[] relationshipsToConsider,
            double x, double y, double width, double height) {
        // FIXME preLayoutAlgorithm
        super.preLayoutAlgorithm(entitiesToLayout, relationshipsToConsider, x, y, width, height);
    }

//    /**
//     * Note, the order in which these elements are added affects the way they
//     * are laid out. We start with the components, add in links to services,
//     * then referenced components, then composite services and references. The
//     * order in which the objects appear in the switchyard.xml file may affect
//     * the layout as well.
//     */
//    private void getLayoutEntities(final Shape shape, Map<Shape, SimpleNode> map, List<SimpleRelationship> relationships) {
//        if (map.containsKey(shape)) {
//            return;
//        }
//        final Object bo = getBusinessObjectForPictogramElement(shape);
//        if (bo instanceof Composite) {
//            final Composite composite = (Composite) bo;
////            for (Component component : getSortedComponents(composite)) {
//                final PictogramElement pe = getFeatureProvider().getPictogramElementForBusinessObject(component);
//                if (pe instanceof Shape) {
//                    getLayoutEntities((Shape) pe, map, relationships);
//                }
////            }
//            for (Service service : composite.getService()) {
//                final PictogramElement pe = getFeatureProvider().getPictogramElementForBusinessObject(service);
//                if (pe instanceof Shape) {
//                    getLayoutEntities((Shape) pe, map, relationships);
//                }
//            }
//            for (Reference reference : composite.getReference()) {
//                final PictogramElement pe = getFeatureProvider().getPictogramElementForBusinessObject(reference);
//                if (pe instanceof Shape) {
//                    getLayoutEntities((Shape) pe, map, relationships);
//                }
//            }
//        } else if (bo instanceof Service) {
//            final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
//            final SimpleNode node = new SimpleNode(shape, ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight());
//            map.put(shape, node);
//            for (Anchor anchor : shape.getAnchors()) {
//                for (Connection connection : anchor.getOutgoingConnections()) {
//                    Shape container = ((Shape) connection.getEnd().getParent()).getContainer();
//                    getLayoutEntities(container, map, relationships);
////                    relationships.add(new ConnectionRelationship(node, map.get(container), false, connection));
//                }
//            }
//        } else if (bo instanceof Component) {
//            final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
//            final SimpleNode node = new SimpleNode(shape, ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight());
//            map.put(shape, node);
//            for (ComponentService service : ((Component) bo).getService()) {
//                PictogramElement pe = getFeatureProvider().getPictogramElementForBusinessObject(service);
//                if (pe instanceof Shape) {
//                    for (Anchor anchor : ((Shape) pe).getAnchors()) {
//                        for (Connection connection : anchor.getIncomingConnections()) {
//                            Shape container = (Shape) connection.getStart().getParent();
//                            if (getBusinessObjectForPictogramElement(container) instanceof Service) {
//                                getLayoutEntities(container, map, relationships);
//                            }
//                        }
//                    }
//                }
//            }
//            for (ComponentReference reference : ((Component) bo).getReference()) {
//                PictogramElement pe = getFeatureProvider().getPictogramElementForBusinessObject(reference);
//                if (pe instanceof Shape) {
//                    for (Anchor anchor : ((Shape) pe).getAnchors()) {
//                        for (Connection connection : anchor.getOutgoingConnections()) {
//                            Shape container = (Shape) connection.getEnd().getParent();
//                            if (getBusinessObjectForPictogramElement(container) instanceof ComponentService) {
//                                container = container.getContainer();
//                            }
//                            getLayoutEntities(container, map, relationships);
////                            relationships.add(new ConnectionRelationship(node, map.get(container), false, connection));
//                        }
//                    }
//                }
//            }
//        } else if (bo instanceof Reference) {
//            final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
//            final SimpleNode node = new SimpleNode(shape, ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight());
//            map.put(shape, node);
//        }
//    }

    private static class Node {
        private Component _bo;
        private Shape _shape;
        private int _row;
        private int _column;
        private int _extent = 1; // depth of the tree
        private int _size = 1; // size of the tree
        private List<Node> _parents = new ArrayList<Node>();
        private List<Node> _children = new ArrayList<Node>();

        private int[] calculateStats(Node node, Set<Node> seen, Set<Node> processed) {
            boolean alreadySeen = !seen.add(node);
            if (processed.add(node)) {
                seen.addAll(node._children);
                for (Node child : node._children) {
                    int[] extentSize = calculateStats(child, seen, processed);
                    node._extent = Math.max(extentSize[0] + 1, node._extent);
                    node._size += extentSize[1];
                }
            }
            if (alreadySeen) {
                return new int[] {1, 1 };
            }
            return new int[] {node._extent, node._size };
        }
    }

    private void buildTrees(Composite composite) {
        for (Component component : composite.getComponent()) {
            createNode(null, component, (Shape) getFeatureProvider().getPictogramElementForBusinessObject(component));
        }
        int maxExtent = 0;
        Set<Node> seen = new HashSet<Node>(_roots);
        Set<Node> processed = new HashSet<Node>();
        for (Node node : _roots) {
            calculateStats(node, seen, processed);
            maxExtent = Math.max(maxExtent, node._extent);
        }

        // sort roots by depth, then size of tree; descending
        Collections.sort(_roots, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int retVal = o2._extent - o1._extent;
                if (retVal == 0) {
                    return o2._size - o1._size;
                }
                return retVal;
            }
        });
    }

    private int[] calculateStats(Node node, Set<Node> seen, Set<Node> processed) {
        // if we've already seen this node, we don't want to include it
        boolean alreadySeen = !seen.add(node);
        if (processed.add(node)) {
            seen.addAll(node._children);
            for (Node child : node._children) {
                int[] extentSize = calculateStats(child, seen, processed);
                node._extent = Math.max(extentSize[0] + 1, node._extent);
                node._size += extentSize[1];
            }
        }
        if (alreadySeen) {
            return new int[] {0, 0 };
        }
        return new int[] {node._extent, node._size };
    }

    private void createNode(Node parent, Component component, Shape shape) {
        if (_nodeMap.containsKey(component)) {
            final Node node = _nodeMap.get(component);
            node._parents.add(parent);
            parent._children.add(node);
            return;
        }
        final Node node = new Node();
        node._bo = component;
        node._shape = shape;
        if (parent == null) {
            _roots.add(node);
        } else {
            node._parents.add(parent);
            parent._children.add(node);
        }
        _nodeMap.put(component, node);
        for (ComponentReference reference : component.getReference()) {
            PictogramElement pe = getFeatureProvider().getPictogramElementForBusinessObject(reference);
            if (pe instanceof Shape) {
                for (Anchor anchor : ((Shape) pe).getAnchors()) {
                    for (Connection connection : anchor.getOutgoingConnections()) {
                        Shape container = (Shape) connection.getStart().getParent();
                        Object bo = getBusinessObjectForPictogramElement(container);
                        if (bo instanceof ComponentService) {
                            createNode(node, (Component) ((ComponentService) bo).eContainer(), (Shape) container);
                        }
                    }
                }
            }
        }
    }

    /**
     * FIXME Comment this
     * 
     * @param container
     * @return
     */
    private Object getBusinessObjectForPictogramElement(Shape container) {
        return null;
    }

    /**
     * FIXME Comment this
     * 
     * @return
     */
    private IFeatureProvider getFeatureProvider() {
        // FIXME getFeatureProvider
        return null;
    }

}
