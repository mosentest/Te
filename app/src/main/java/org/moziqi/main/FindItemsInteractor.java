package org.moziqi.main;

import org.moziqi.interactor.GeneralInteractor;

/**
 * Created by moziqi on 15-8-12.
 */
public interface FindItemsInteractor extends GeneralInteractor {

    public void findItems(OnFinishedListener listener);

}
