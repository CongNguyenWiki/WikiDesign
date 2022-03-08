package com.skypremiuminternational.app.app.internal.di.forgot_password;

import com.skypremiuminternational.app.app.features.forgot_password.ForgotPasswordActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ForgotPasswordSubComponent
        extends AndroidInjector<ForgotPasswordActivity> {

    @Subcomponent.Factory
    abstract class Factory implements AndroidInjector.Factory<ForgotPasswordActivity> { }
}
