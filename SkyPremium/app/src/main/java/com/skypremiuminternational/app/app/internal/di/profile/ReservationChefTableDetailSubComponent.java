package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.detail_cheftable_reservation.DetailsReservationChefTableActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ReservationChefTableDetailSubComponent extends AndroidInjector<DetailsReservationChefTableActivity> {

    @Subcomponent.Factory
    abstract class Factory implements AndroidInjector.Factory<DetailsReservationChefTableActivity> {}

}