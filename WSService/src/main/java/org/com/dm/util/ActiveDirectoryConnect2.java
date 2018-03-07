package org.com.dm.util;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class ActiveDirectoryConnect2 {

	 public static void main(String[] args) throws NamingException {
	        
	        final String ldapAdServer = "ldap://10.11.10.201:389";
//	        final String ldapSearchBase = "dc=ad,dc=derrama,dc=com";
	        final String ldapSearchBase = "DC=derrama,DC=org,DC=pe";
	        
	        
	        final String ldapUsername = "accessldap";
	        final String ldapPassword = "4cc3ssld4p";
	        
	        final String ldapAccountToLookup = "jmatos";
	        
	        
	        Hashtable<String, Object> env = new Hashtable<String, Object>();
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        if(ldapUsername != null) {
	            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
	        }
	        if(ldapPassword != null) {
	            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
	        }
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, ldapAdServer);

	        //ensures that objectSID attribute values
	        //will be returned as a byte[] instead of a String
	        env.put("java.naming.ldap.attributes.binary", "objectSID");
	        
	        // the following is helpful in debugging errors
	        //env.put("com.sun.jndi.ldap.trace.ber", System.err);
	        
	        LdapContext ctx = new InitialLdapContext(env, null);
	        		
	        ActiveDirectoryConnect2 ldap = new ActiveDirectoryConnect2();
	 
	        //verificando otro usuario
//	        Hashtable environment = (Hashtable)ctx.getEnvironment().clone();
//	        environment.put(Context.SECURITY_PRINCIPAL, "jmatos");
//	        environment.put(Context.SECURITY_CREDENTIALS, "fitbank123");
//	        LdapContext ctx2 = new InitialLdapContext(environment, null);
//	        System.out.println(ctx2);
	        
	        //1) lookup the ldap account
	        SearchResult srLdapUser = ldap.findAccountByAccountName(ctx, ldapSearchBase, ldapAccountToLookup);
	        System.out.println("1. srLdapUser : " + srLdapUser);
	        //2) get the SID of the users primary group
	        String primaryGroupSID = ldap.getPrimaryGroupSID(srLdapUser);
	        System.out.println("2. primaryGroupSID : " + primaryGroupSID);
	        //3) get the users Primary Group
	        String primaryGroupName = ldap.findGroupBySID(ctx, ldapSearchBase, primaryGroupSID);
	        System.out.println("3. primaryGroupName : " + primaryGroupName);
	    }
	 
	 public static void main1(String[] args) throws NamingException {
	        
		 final String ldapAdServer = "ldap://10.11.10.201:389";
	        final String ldapSearchBase = "DC=derrama,DC=org,DC=pe";
	        
	        final String ldapUsername = "accessldap";
	        final String ldapPassword = "4cc3ssld4p";
	        
	        final String ldapAccountToLookup = "jmatos";
	        
	        
	        Hashtable<String, Object> env = new Hashtable<String, Object>();
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        if(ldapUsername != null) {
	            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
	        }
	        if(ldapPassword != null) {
	            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
	        }
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, ldapAdServer);

	        //ensures that objectSID attribute values
	        //will be returned as a byte[] instead of a String
	        env.put("java.naming.ldap.attributes.binary", "objectSID");
	        
	        // the following is helpful in debugging errors
	        //env.put("com.sun.jndi.ldap.trace.ber", System.err);
	        
	        LdapContext ctx = new InitialLdapContext();
	        
	        ActiveDirectoryConnect2 ldap = new ActiveDirectoryConnect2();
	        
	        //1) lookup the ldap account
	        SearchResult srLdapUser = ldap.findAccountByAccountName(ctx, ldapSearchBase, ldapAccountToLookup);
	        
	        //2) get the SID of the users primary group
	        String primaryGroupSID = ldap.getPrimaryGroupSID(srLdapUser);
	        
	        //3) get the users Primary Group
	        String primaryGroupName = ldap.findGroupBySID(ctx, ldapSearchBase, primaryGroupSID);
	    }
	 
	 
	 
	 
	 
	    
	    public SearchResult findAccountByAccountName(DirContext ctx, String ldapSearchBase, String accountName) throws NamingException {

	        String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";

	        SearchControls searchControls = new SearchControls();
	        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

	        NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);

	        SearchResult searchResult = null;
	        if(results.hasMoreElements()) {
	             searchResult = (SearchResult) results.nextElement();

	            //make sure there is not another item available, there should be only 1 match
	            if(results.hasMoreElements()) {
	                System.err.println("Matched multiple users for the accountName: " + accountName);
	                return null;
	            }
	        }
	        
	        return searchResult;
	    }
	    
	    public String findGroupBySID(DirContext ctx, String ldapSearchBase, String sid) throws NamingException {
	        
	        String searchFilter = "(&(objectClass=group)(objectSid=" + sid + "))";

	        SearchControls searchControls = new SearchControls();
	        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	        
	        NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);

	        if(results.hasMoreElements()) {
	            SearchResult searchResult = (SearchResult) results.nextElement();

	            //make sure there is not another item available, there should be only 1 match
	            if(results.hasMoreElements()) {
	                System.err.println("Matched multiple groups for the group with SID: " + sid);
	                return null;
	            } else {
	                return (String)searchResult.getAttributes().get("sAMAccountName").get();
	            }
	        }
	        return null;
	    }
	    
	    public String getPrimaryGroupSID(SearchResult srLdapUser) throws NamingException {
	        byte[] objectSID = (byte[])srLdapUser.getAttributes().get("objectSid").get();
	        String strPrimaryGroupID = (String)srLdapUser.getAttributes().get("primaryGroupID").get();
	        
	        String strObjectSid = decodeSID(objectSID);
	        
	        return strObjectSid.substring(0, strObjectSid.lastIndexOf('-') + 1) + strPrimaryGroupID;
	    }
	    
	    /**
	     * The binary data is in the form:
	     * byte[0] - revision level
	     * byte[1] - count of sub-authorities
	     * byte[2-7] - 48 bit authority (big-endian)
	     * and then count x 32 bit sub authorities (little-endian)
	     * 
	     * The String value is: S-Revision-Authority-SubAuthority[n]...
	     * 
	     * Based on code from here - http://forums.oracle.com/forums/thread.jspa?threadID=1155740&tstart=0
	     */
	    public static String decodeSID(byte[] sid) {
	        
	        final StringBuilder strSid = new StringBuilder("S-");

	        // get version
	        final int revision = sid[0];
	        strSid.append(Integer.toString(revision));
	        
	        //next byte is the count of sub-authorities
	        final int countSubAuths = sid[1] & 0xFF;
	        
	        //get the authority
	        long authority = 0;
	        //String rid = "";
	        for(int i = 2; i <= 7; i++) {
	           authority |= ((long)sid[i]) << (8 * (5 - (i - 2)));
	        }
	        strSid.append("-");
	        strSid.append(Long.toHexString(authority));
	        
	        //iterate all the sub-auths
	        int offset = 8;
	        int size = 4; //4 bytes for each sub auth
	        for(int j = 0; j < countSubAuths; j++) {
	            long subAuthority = 0;
	            for(int k = 0; k < size; k++) {
	                subAuthority |= (long)(sid[offset + k] & 0xFF) << (8 * k);
	            }
	            
	            strSid.append("-");
	            strSid.append(subAuthority);
	            
	            offset += size;
	        }
	        
	        return strSid.toString();    
	    }
}
