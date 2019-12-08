package com.wego.parkingmanagement.utils;

import com.wego.parkingmanagement.model.CarParks;

import java.util.Collections;
import java.util.List;

public class PaginationUtlils {


    /**
     *
     * @param page
     * @param itemsPerPage
     * @param carParks
     * @return
     * This method helps to provide the paginated results
     */
    public static  List<CarParks> getPaginatedResult(Integer page, Integer itemsPerPage, List<CarParks> carParks) {

        int start = (page - 1) * itemsPerPage + 1;
        int totalItems = carParks.size();
        int end = totalItems;
        int total_pages = (int) Math.ceil((double) carParks.size() / itemsPerPage);
        if (page > 0 && page <= total_pages) {
            if (itemsPerPage < totalItems) {
                end = itemsPerPage * page;
                if (end > totalItems) {
                    end = totalItems;
                }
            }

            return carParks.subList(start - 1, end);
        }

        return Collections.emptyList();
    }







}
