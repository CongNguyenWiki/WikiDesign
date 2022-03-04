package com.skypremiuminternational.app.app.internal.di.faq;

import com.skypremiuminternational.app.app.features.faq.FaqActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface FaqSubComponent
        extends AndroidInjector<FaqActivity> {

    @Subcomponent.Factory
    abstract class Factory implements AndroidInjector.Factory<FaqActivity> { }
}
