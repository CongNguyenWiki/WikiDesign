package com.skypremiuminternational.app.app.internal.di;

import android.content.Context;

import com.skypremiuminternational.app.app.App;
import com.skypremiuminternational.app.app.UIThread;
import com.skypremiuminternational.app.app.impl.DBManagerImpl;
import com.skypremiuminternational.app.app.impl.InternalStorageManagerImpl;
import com.skypremiuminternational.app.app.impl.NetworkManagerImpl;
import com.skypremiuminternational.app.app.internal.di.booking.BookingDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.BookingHistorySubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.BookingSummarySubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.CancellationPolicySubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.CheckoutGuestDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.CheckoutPaymentReviewSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.DateRangePickerSubcomponent;
import com.skypremiuminternational.app.app.internal.di.booking.HotelBookingSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.OccupancyPickerSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.OrderPlacedSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.PropertyDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.RoomCheckoutSubComponent;
import com.skypremiuminternational.app.app.internal.di.booking.RoomDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.cart.ShoppingCartSubComponent;
import com.skypremiuminternational.app.app.internal.di.faq.FaqSubComponent;
import com.skypremiuminternational.app.app.internal.di.forgot_password.ForgotPasswordSubComponent;
import com.skypremiuminternational.app.app.internal.di.forgot_password.UpdatePasswordSubcomponent;
import com.skypremiuminternational.app.app.internal.di.forgot_password.UpdateSuccessSubComponent;
import com.skypremiuminternational.app.app.internal.di.landing.LandingSubComponent;
import com.skypremiuminternational.app.app.internal.di.membership.MembershipStatementSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_favourite.MyFavouriteSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_favourite.MyFavouritesDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_orders.EditReviewSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_orders.MyOrdersSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_orders.OrderDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_orders.ReviewSubComponent;
import com.skypremiuminternational.app.app.internal.di.my_orders.SeeReviewSubComponent;
import com.skypremiuminternational.app.app.internal.di.navigation.NavigationSubComponent;
import com.skypremiuminternational.app.app.internal.di.product.ProductDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.product.ProductSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ConfirmReservationSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.DateSinglePickerSubcomponent;
import com.skypremiuminternational.app.app.internal.di.profile.EditProfileSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.MakeAReservationSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ManageCreditCardSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ManageDeliveryAddressSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ProfileSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ReservationChefTableDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ReservationDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ReservationSubComponent;
import com.skypremiuminternational.app.app.internal.di.profile.ReservationTopTableDetailSubComponent;
import com.skypremiuminternational.app.app.internal.di.signin.SignInSubComponent;
import com.skypremiuminternational.app.app.internal.di.splash.SplashSubComponent;
import com.skypremiuminternational.app.data.DBManager;
import com.skypremiuminternational.app.data.DbHelper;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.JobExecutor;
import com.skypremiuminternational.app.data.NetworkManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module(subcomponents =
        {
                SplashSubComponent.class,
                SignInSubComponent.class,
                LandingSubComponent.class,
                ProfileSubComponent.class,
                NavigationSubComponent.class,
                ProductSubComponent.class,
                ProductDetailSubComponent.class,
                MyFavouriteSubComponent.class,
                ShoppingCartSubComponent.class,
                MyFavouriteSubComponent.class,
                EditProfileSubComponent.class,
                UpdatePasswordSubcomponent.class,
                MyOrdersSubComponent.class,
                OrderDetailSubComponent.class,
                ReviewSubComponent.class,
                SeeReviewSubComponent.class,
                EditReviewSubComponent.class,
                MembershipStatementSubComponent.class,
                ManageDeliveryAddressSubComponent.class,
                ManageCreditCardSubComponent.class,
                ReservationSubComponent.class,
                ReservationDetailSubComponent.class,
                MakeAReservationSubComponent.class,
                DateSinglePickerSubcomponent.class,
                ConfirmReservationSubComponent.class,
                MyFavouritesDetailSubComponent.class,
                HotelBookingSubComponent.class,
                DateRangePickerSubcomponent.class,
                BookingDetailSubComponent.class,
                PropertyDetailSubComponent.class,
                RoomDetailSubComponent.class,
                BookingHistorySubComponent.class,
                OccupancyPickerSubComponent.class,
                BookingSummarySubComponent.class,
                RoomCheckoutSubComponent.class,
                CheckoutGuestDetailSubComponent.class,
                CheckoutPaymentReviewSubComponent.class,
                OrderPlacedSubComponent.class,
                CancellationPolicySubComponent.class,
                FaqSubComponent.class,
                ForgotPasswordSubComponent.class,
                UpdateSuccessSubComponent.class,
                ReservationChefTableDetailSubComponent.class,
                ReservationTopTableDetailSubComponent.class
        })
public abstract class ApplicationModule {

    static @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    static @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    static @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    static @Provides
    @Singleton
    DBManager provideDBManager(DbHelper dbHelper) {
        return new DBManagerImpl(dbHelper);
    }

    abstract @Binds
    NetworkManager bindNetworkManager(
            NetworkManagerImpl networkManager);

    @Binds
    abstract InternalStorageManager bindInternalStorageManager(
            InternalStorageManagerImpl internalStorageManager);
}
