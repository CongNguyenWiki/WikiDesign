package com.skypremiuminternational.app.data.network.request;

public class GetMyMembershipStatementRequest {


    public final String selectedCategory;
    public final String selectedSorting;
    public final String memberNumber;
    public final String limit;
    public final String currentPage;


    public GetMyMembershipStatementRequest(String selectedCategory, String selectedSorting, String memberNumber, String limit, String currentPage) {
        this.selectedCategory = selectedCategory;
        this.selectedSorting = selectedSorting;
        this.memberNumber = memberNumber;
        this.limit = limit;
        this.currentPage = currentPage;
    }

    public static GetMyMembershipStatementRequest getWithFILTER(String selectedCategory, String selectedSorting, String memberNumber, String limit, String currentPage) {
        return new GetMyMembershipStatementRequest(selectedCategory, selectedSorting,memberNumber,limit,currentPage);
    }


}
