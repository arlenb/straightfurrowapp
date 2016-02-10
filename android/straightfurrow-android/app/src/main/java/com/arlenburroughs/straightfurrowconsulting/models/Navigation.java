package com.arlenburroughs.straightfurrowconsulting.models;

import com.arlenburroughs.straightfurrowconsulting.fragments.HomeFragment;

import java.util.LinkedList;

/**
 * Created by wdian on 12/3/14.
 */
public class Navigation {

    /** NAVIGATION **/
    private static LinkedList<NavigationItem> navigationItems;

    public static LinkedList<NavigationItem> getNavigationItems() {
        navigationItems = new LinkedList<NavigationItem>();

        // Add fragmentItems here
        //TODO create frags and add them here.
        add("Home", HomeFragment.class);

        return navigationItems;
    }

    private static void add(String title, Class fragmentClass) {
        NavigationItem navigationItem = new NavigationItem();
        navigationItem.title = title;
        navigationItem.fragmentClass = fragmentClass;
        navigationItems.add(navigationItem);
    }

    public static class NavigationItem {
        public String title;
        public Class fragmentClass;
    }

}
