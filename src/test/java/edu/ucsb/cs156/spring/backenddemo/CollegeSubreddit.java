package edu.ucsb.cs156.spring.backenddemo.beans;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

/**
 * A bean to support reading CSV data from 
 * https://raw.githubusercontent.com/karlding/college-subreddits/master/data/colleges.csv
 * 
 */

@Data
public class CollegeSubreddit {

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String location;

    @CsvBindByName
    private String subreddit;

}