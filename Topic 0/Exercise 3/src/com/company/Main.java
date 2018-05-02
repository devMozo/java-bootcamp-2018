package com.company;

import com.company.dbs.DBProxy;
/**
 * Represents the creation of a proxy example for database accesor clases.
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class Main {
    /**
     * Main..
     * @param args
     */
    public static void main(String[] args){
		// New Proxy
    	DBProxy oDbProxy = new DBProxy();
		// Connecttt to the DATABASE!!
		oDbProxy.connect();
    }
}