package com.skypremiuminternational.app.app.internal.di.forgot_password;


import com.skypremiuminternational.app.app.features.forgot_password.success.ForgotPasswordSuccessDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface UpdateSuccessSubComponent
        extends AndroidInjector<ForgotPasswordSuccessDialogFragment> {

    @Subcomponent.Factory
    abstract class Factory implements AndroidInjector.Factory<ForgotPasswordSuccessDialogFragment> { }
}
