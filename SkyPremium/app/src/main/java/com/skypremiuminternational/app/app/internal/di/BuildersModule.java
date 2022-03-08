package com.skypremiuminternational.app.app.internal.di;


import com.skypremiuminternational.app.app.features.faq.FaqActivity;
import com.skypremiuminternational.app.app.features.forgot_password.ForgotPasswordActivity;
import com.skypremiuminternational.app.app.features.forgot_password.success.ForgotPasswordSuccessDialogFragment;
import com.skypremiuminternational.app.app.features.forgot_password.update.UpdatePasswordActivity;
import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.features.membership.MembershipStatementActivity;
import com.skypremiuminternational.app.app.features.my_favourite.MyFavouriteActivity;
import com.skypremiuminternational.app.app.features.my_favourite.detail.MyFavouritesDetailActivity;
import com.skypremiuminternational.app.app.features.my_orders.MyOrderActivity;
import com.skypremiuminternational.app.app.features.my_orders.detail.OrderDetailsActivity;
import com.skypremiuminternational.app.app.features.my_orders.detail.edit_review.EditReviewDialogFragment;
import com.skypremiuminternational.app.app.features.my_orders.detail.review.ReviewDialogFragment;
import com.skypremiuminternational.app.app.features.my_orders.detail.see_review.ReviewContentDialogFragment;
import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;
import com.skypremiuminternational.app.app.features.product.ProductActivity;
import com.skypremiuminternational.app.app.features.profile.booking.HotelBookingDialogFragment;
import com.skypremiuminternational.app.app.features.profile.booking.cancellationpolicy.CancellationPolicyDialogFragment;
import com.skypremiuminternational.app.app.features.profile.booking.daterangepicker.DateRangePickerDialogFragment;
import com.skypremiuminternational.app.app.features.profile.booking.detail.BookingDetailActivity;
import com.skypremiuminternational.app.app.features.profile.booking.detail.property.PropertyDetailActivity;
import com.skypremiuminternational.app.app.features.profile.booking.detail.room.RoomDetailDialogFragment;
import com.skypremiuminternational.app.app.features.profile.booking.history.BookingsHistoryActivity;
import com.skypremiuminternational.app.app.features.profile.booking.occupancy.OccupancyPickerDialogFragment;
import com.skypremiuminternational.app.app.features.profile.booking.room.RoomCheckoutActivity;
import com.skypremiuminternational.app.app.features.profile.booking.room.stepone.CheckoutGuestDetailFragment;
import com.skypremiuminternational.app.app.features.profile.booking.room.stepthree.OrderPlacedFragment;
import com.skypremiuminternational.app.app.features.profile.booking.room.steptwo.CheckoutPaymentReviewFragment;
import com.skypremiuminternational.app.app.features.profile.booking.summary.BookingSummaryActivity;
import com.skypremiuminternational.app.app.features.profile.edit_profile.EditProfileActivity;
import com.skypremiuminternational.app.app.features.profile.manage_credit_card.ManageCreditCardActivity;
import com.skypremiuminternational.app.app.features.profile.manage_delivery_address.ManageDeliveryAddressActivity;
import com.skypremiuminternational.app.app.features.profile.my_profile.ProfileActivity;
import com.skypremiuminternational.app.app.features.product.detail.ProductDetailActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.MyResevationsActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.confirm_create_reservation.ConfirmReservationDialogFragment;
import com.skypremiuminternational.app.app.features.profile.my_reservation.datesinglepicker.DateSinglePickerDialogFragment;
import com.skypremiuminternational.app.app.features.profile.my_reservation.detail.DetailsResevationActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.detail_cheftable_reservation.DetailsReservationChefTableActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.detail_toptable_reservation.DetailsReservationTopTableActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.make_reservation.MakeAReservationDialogFragment;
import com.skypremiuminternational.app.app.features.shopping_cart.ShoppingCartActivity;
import com.skypremiuminternational.app.app.features.signin.SignInActivity;
import com.skypremiuminternational.app.app.features.splash.SplashActivity;
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

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;


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
public abstract class BuildersModule {

    @Binds
    @IntoMap
    @ClassKey(SplashActivity.class)
    abstract AndroidInjector.Factory<?> bindSplashActivityInjectorFactory(
            SplashSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(SignInActivity.class)
    abstract AndroidInjector.Factory<?> bindSignInActivityInjectorFactory(
            SignInSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(LandingActivity.class)
    abstract AndroidInjector.Factory<?> bindLandingActivityInjectorFactory(
            LandingSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(ProfileActivity.class)
    abstract AndroidInjector.Factory<?> bindProfileActivityInjectorFactory(
            ProfileSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(UpdatePasswordActivity.class)
    abstract AndroidInjector.Factory<?> bindUpdatePasswordActivityInjectorFactory(
            UpdatePasswordSubcomponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(NavigationDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindNavigationFragmentInjectorFactory(
            NavigationSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(ProductActivity.class)
    abstract AndroidInjector.Factory<?> bindProductActivityInjectorFactory(
            ProductSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(ProductDetailActivity.class)
    abstract AndroidInjector.Factory<?> bindProductDetailActivityInjectorFactory(
            ProductDetailSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(MyFavouriteActivity.class)
    abstract AndroidInjector.Factory<?> bindMyFavouriteActivityInjectorFactory(
            MyFavouriteSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(ShoppingCartActivity.class)
    abstract AndroidInjector.Factory<?> bindMyShoppingCartActivityInjectorFactory(
            ShoppingCartSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(EditProfileActivity.class)
    abstract AndroidInjector.Factory<?> bindEditProfileActivityInjectorFactory(
            EditProfileSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(MyOrderActivity.class)
    abstract AndroidInjector.Factory<?> bindMyOrderActivityInjectorFactory(
            MyOrdersSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(OrderDetailsActivity.class)
    abstract AndroidInjector.Factory<?> bindOrderDetailActivityInjectorFactory(
            OrderDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(ReviewDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindReviewDialogFragmentInjectorFactory(
            ReviewSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(EditReviewDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindEditReviewDialogFragmentInjectorFactory(
            EditReviewSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(ReviewContentDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindSeeReviewDialogFragmentInjectorFactory(
            SeeReviewSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(MembershipStatementActivity.class)
    abstract AndroidInjector.Factory<?> bindMembershipStatementActivityInjectorFactory(
            MembershipStatementSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(ManageDeliveryAddressActivity.class)
    abstract AndroidInjector.Factory<?> bindManageDeliveryAddressActivityInjectorFactory(
            ManageDeliveryAddressSubComponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(ManageCreditCardActivity.class)
    abstract AndroidInjector.Factory<?> bindManageCreditCardActivityInjectorFactory(
            ManageCreditCardSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(MyResevationsActivity.class)
    abstract AndroidInjector.Factory<?> bindReservationActivityInjectorFactory(
            ReservationSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(DetailsResevationActivity.class)
    abstract AndroidInjector.Factory<?> bindReservationDetailActivityInjectorFactory(
            ReservationDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(MakeAReservationDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindMakeAReservationFragmentInjectorFactory(
            MakeAReservationSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(DateSinglePickerDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindDateSinglePickerDialogFragmentInjectorFactory(
            DateSinglePickerSubcomponent.Factory builder);


    @Binds
    @IntoMap
    @ClassKey(ConfirmReservationDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindConfirmReservationFragmentInjectorFactory(
            ConfirmReservationSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(MyFavouritesDetailActivity.class)
    abstract AndroidInjector.Factory<?> bindMyFavouritesActivityInjectorFactory(
            MyFavouritesDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(HotelBookingDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindHotelBookingDialogFragmentInjectorFactory(
            HotelBookingSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(DateRangePickerDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindDateRangePickerDialogFragmentInjectorFactory(
            DateRangePickerSubcomponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(BookingDetailActivity.class)
    abstract AndroidInjector.Factory<?> bindBookingDetailActivityInjectorFactory(
            BookingDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(PropertyDetailActivity.class)
    abstract AndroidInjector.Factory<?> bindPropertyDetailActivityInjectorFactory(
            PropertyDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(RoomDetailDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindRoomDetailDialogFragmentInjectorFactory(
            RoomDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(BookingsHistoryActivity.class)
    abstract AndroidInjector.Factory<?> bindBookingsHistoryActivityInjectorFactory(
            BookingHistorySubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(OccupancyPickerDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindOccupancyPickerDialogFragmentInjectorFactory(
            OccupancyPickerSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(BookingSummaryActivity.class)
    abstract AndroidInjector.Factory<?> bindBookingSummaryActivityInjectorFactory(
            BookingSummarySubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(RoomCheckoutActivity.class)
    abstract AndroidInjector.Factory<?> bindRoomCheckoutActivityInjectorFactory(
            RoomCheckoutSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(CheckoutGuestDetailFragment.class)
    abstract AndroidInjector.Factory<?> bindCheckoutGuestDetailFragmentInjectorFactory(
            CheckoutGuestDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(CheckoutPaymentReviewFragment.class)
    abstract AndroidInjector.Factory<?> bindCheckoutPaymentReviewFragmentInjectorFactory(
            CheckoutPaymentReviewSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(OrderPlacedFragment.class)
    abstract AndroidInjector.Factory<?> bindOrderPlacedFragmentInjectorFactory(
            OrderPlacedSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(CancellationPolicyDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindCancellationPolicyDialogFragmentInjectorFactory(
            CancellationPolicySubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(FaqActivity.class)
    abstract AndroidInjector.Factory<?> bindFaqActivityInjectorFactory(
            FaqSubComponent.Factory builder);

  @Binds
  @IntoMap
  @ClassKey(ForgotPasswordActivity.class)
  abstract AndroidInjector.Factory<?> bindForgotPasswordActivityInjectorFactory(
          ForgotPasswordSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(ForgotPasswordSuccessDialogFragment.class)
    abstract AndroidInjector.Factory<?> bindForgotPasswordSuccessDialogFragmentInjectorFactory(
            UpdateSuccessSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(DetailsReservationTopTableActivity.class)
    abstract AndroidInjector.Factory<?> bindDetailTopTableReservationActivityInjectorFactory(
            ReservationTopTableDetailSubComponent.Factory builder);

    @Binds
    @IntoMap
    @ClassKey(DetailsReservationChefTableActivity.class)
    abstract AndroidInjector.Factory<?> bindDetailChefTableReservationActivityInjectorFactory(
            ReservationChefTableDetailSubComponent.Factory builder);



}

