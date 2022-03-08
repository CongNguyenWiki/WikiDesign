package com.skypremiuminternational.app.app.features.my_favourite;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.listener.SaveImageActionListener;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class FavPartnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SaveImageActionListener {

    private static final int TOP_LAYOUT = 1;
    private static final int CHEF_LAYOUT = 3;
    private static final int P_LAYOUT = 2;


    private final CompositeSubscription compositeSubscription = new CompositeSubscription();


    private List<FavouriteItem> dataList = new ArrayList<>();
    private FavPartnerAdapter.ActionListener actionListener;

    public void setActionListener(FavPartnerAdapter.ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public List<FavouriteItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<FavouriteItem> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public FavouriteItem getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == P_LAYOUT) {

            /**
             *  ============ P_LAYOUT
             */

            final View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_partner_full_size, parent, false);
            final MyFavPartnerFullSizeHolder viewHolder = new MyFavPartnerFullSizeHolder(view);
            viewHolder.setListener(this);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
                    }
                }
            });

            viewHolder.tvViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));

                }
            });
            viewHolder.tvBenefits.setOnClickListener(v -> {
                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
                }
            });
            viewHolder.tvWhatUnique.setOnClickListener(v -> {
                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
                }
            });

            viewHolder.ivFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        actionListener.onClickedFavourite(dataList.get(viewHolder.getAdapterPosition()));
                    }
                }
            });

            viewHolder.tvReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        actionListener.onReserveNow(dataList.get(viewHolder.getAdapterPosition()),
                                viewHolder.getAdapterPosition(),viewHolder);
                    }
                }
            });
            return viewHolder;
        }
//        else if (viewType == CHEF_LAYOUT) {
//            /**
//             *  ============ CHEF_LAYOUT
//             */
//
//            final View view =
//                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cheftable_new, parent, false);
//            final MyFavChefFullSizeHolder viewHolder = new MyFavChefFullSizeHolder(view);
//            viewHolder.setListener(this);
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                        actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
//                    }
//                }
//            });
//            viewHolder.tvViewDetail.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
//                }
//            });
//
//            viewHolder.tvMenuDescription.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
//                }
//            });
//
//            viewHolder.tvViewMenu.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onItemClickedWithViewMenu(dataList.get(viewHolder.getAdapterPosition()));
//                }
//            });
//
//            viewHolder.tvRequestNow.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onChefRequestNow(dataList.get(viewHolder.getAdapterPosition()),
//                            viewHolder.getAdapterPosition(),viewHolder);
//                }
//            });
//            viewHolder.imgFav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                        actionListener.onClickedFavourite(dataList.get(viewHolder.getAdapterPosition()));
//                    }
//                }
//            });
//            return viewHolder;
//        }
//        else if (viewType == TOP_LAYOUT) {
//            /**
//             *  ============ TOP_LAYOUT
//             */
//
//            final View view =
//                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toptable_new, parent, false);
//            final MyFavTopTableFullSizeHolder viewHolder = new MyFavTopTableFullSizeHolder(view);
//            viewHolder.setListener(this);
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                        actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
//                    }
//                }
//            });
//
//            viewHolder.tvViewDetail.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
//                }
//            });
//
//            viewHolder.tvViewMenu.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onItemClickedWithViewMenu(dataList.get(viewHolder.getAdapterPosition()));
//                }
//            });
//
//            viewHolder.tvRequestNow.setOnClickListener(v -> {
//                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    actionListener.onTopRequestNow(dataList.get(viewHolder.getAdapterPosition()),
//                            viewHolder.getAdapterPosition(),viewHolder);
//                }
//            });
//            viewHolder.imgFav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//                        actionListener.onClickedFavourite(dataList.get(viewHolder.getAdapterPosition()));
//                    }
//                }
//            });
//            return viewHolder;
//        }
        else {

            /**
             *  ============ P_LAYOUT
             */

            final View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_partner_full_size, parent, false);
            final MyFavPartnerFullSizeHolder viewHolder = new MyFavPartnerFullSizeHolder(view);
            viewHolder.setListener(this);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
                    }
                }
            });

            viewHolder.tvViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));

                }
            });
            viewHolder.tvBenefits.setOnClickListener(v -> {
                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
                }
            });
            viewHolder.tvWhatUnique.setOnClickListener(v -> {
                if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    actionListener.onClickedItem(dataList.get(viewHolder.getAdapterPosition()));
                }
            });

            viewHolder.ivFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionListener != null && viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        actionListener.onClickedFavourite(dataList.get(viewHolder.getAdapterPosition()));
                    }
                }
            });
            return viewHolder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder  holder, int position) {

        Subscription subscription = null;
//        if (holder instanceof MyFavTopTableFullSizeHolder) {
//            MyFavTopTableFullSizeHolder myFavTopTableFullSizeHolder = (MyFavTopTableFullSizeHolder) holder;
//            subscription = myFavTopTableFullSizeHolder.bind(dataList.get(position));
//            myFavTopTableFullSizeHolder.bind(dataList.get(position));
//            if (subscription != null) {
//                compositeSubscription.add(subscription);
//            }
//        }
//        if (holder instanceof MyFavChefFullSizeHolder) {
//            MyFavChefFullSizeHolder myFavChefFullSizeHolder = (MyFavChefFullSizeHolder) holder;
//            myFavChefFullSizeHolder.bind(dataList.get(position));
//        }
        if (holder instanceof MyFavPartnerFullSizeHolder){
            MyFavPartnerFullSizeHolder partnerFullSizeHolder = (MyFavPartnerFullSizeHolder) holder;
            partnerFullSizeHolder.bind(dataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String attributeSetName = "";
        if (this.dataList.get(position).getProduct().getAttributeSetName() != null) {
            attributeSetName = this.dataList.get(position).getProduct().getAttributeSetName();
        }

        if (!TextUtils.isEmpty(attributeSetName)) {
//            if (attributeSetName.equalsIgnoreCase(Constants.ATT_SET_CHEF)) {
//                return CHEF_LAYOUT;
//            } else if (attributeSetName.equalsIgnoreCase(Constants.ATT_SET_TOP)) {
//                return TOP_LAYOUT;
//            } else if (attributeSetName.equalsIgnoreCase(Constants.ATT_SET_PARTNER)) {
//                return P_LAYOUT;
//            } else {
//                return P_LAYOUT;
//            }
            return P_LAYOUT;

        }
        return P_LAYOUT;
    }

    public void addAll(List<FavouriteItem> dataObj) {
        dataList.addAll(dataObj);
        notifyDataSetChanged();
    }

    public void setData(List<FavouriteItem> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public void saveImgPosition(int position, int imgPos) {
        dataList.get(position).setImgPos(imgPos);
    }



    interface ActionListener<T> {
        void onClickedItem(FavouriteItem item);

        void onClickedFavourite(FavouriteItem item);
//
//        void onChefRequestNow(FavouriteItem item , int position, MyFavChefFullSizeHolder holder);
//
//        void onTopRequestNow(FavouriteItem item , int position, MyFavTopTableFullSizeHolder holder);

        void onItemClickedWithViewMenu(FavouriteItem item);

        void onReserveNow(FavouriteItem item , int position, MyFavPartnerFullSizeHolder holder);


    }


}
