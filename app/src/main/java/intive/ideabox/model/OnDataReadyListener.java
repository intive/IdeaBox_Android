package intive.ideabox.model;

import java.util.List;

public interface OnDataReadyListener {
    void onDataReady(List<IdeaData> ideaData);

    void onError(String errorMsg);
}
