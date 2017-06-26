package org.cwi.examine.presentation.main;

import javafx.scene.layout.Region;
import org.cwi.examine.model.Network;
import org.cwi.examine.presentation.Section;

public class MainSection implements Section {

    private final Network superNetwork;

    private final MainView view;
    private final MainViewModel viewModel;

    public MainSection(final Network superNetwork) {
        this.superNetwork = superNetwork;

        this.view = new MainView();
        this.viewModel = new MainViewModel();

        bindViewModel();

        initializeActiveNetwork();
    }

    private void bindViewModel() {

        view.getAnnotationOverview().categoriesProperty().bind(viewModel.activeCategoriesProperty());
        view.getNodeLinkContourView().networkProperty().bind(viewModel.activeNetworkProperty());
    }

    /**
     * Initialize active network that is visualized. For now it is the union of all known modules.
     */
    private void initializeActiveNetwork() {

        final Network moduleNetwork = Network.induce(superNetwork.modules, superNetwork);

        viewModel.activateNetwork(moduleNetwork);

        System.out.println(superNetwork.graph.vertexSet().size());
        System.out.println(moduleNetwork.graph.vertexSet().size());
    }

    @Override
    public Region getView() {
        return view;
    }

}