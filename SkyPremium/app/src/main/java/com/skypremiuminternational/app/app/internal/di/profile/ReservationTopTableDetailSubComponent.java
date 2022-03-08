package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.detail_toptable_reservation.DetailsReservationTopTableActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ReservationTopTableDetailSubComponent extends AndroidInjector<DetailsReservationTopTableActivity> {

    @Subcomponent.Factory
    abstract class Factory implements AndroidInjector.Factory<DetailsReservationTopTableActivity> {}

}
