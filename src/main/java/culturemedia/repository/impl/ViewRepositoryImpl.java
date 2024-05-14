package culturemedia.repository.impl;

import culturemedia.model.View;
import culturemedia.repository.ViewRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewRepositoryImpl implements ViewRepository {
    private final List<View> views;

    public ViewRepositoryImpl() {
        this.views = new ArrayList<>();
    }

    @Override
    public View add(View view) {
        this.views.add( view );
        return view;
    }
}
