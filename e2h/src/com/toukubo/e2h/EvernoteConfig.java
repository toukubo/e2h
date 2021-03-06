package com.toukubo.e2h;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.UserStoreClient;
import com.evernote.thrift.TException;
import com.evernote.thrift.transport.TTransportException;

public class EvernoteConfig {
	EvernoteAuth evernoteAuth = null;
	private ClientFactory factory;
	private UserStoreClient userStore;
	static final String AUTH_TOKEN = "S=s21:U=23ac22:E=14dc8e52921:C=1467133fd26:P=1cd:A=en-devtoken:V=2:H=5dd0474816594c1bebb82d7ec612c95f";

	public EvernoteConfig(){

		evernoteAuth = new EvernoteAuth(EvernoteService.PRODUCTION, AUTH_TOKEN);
	    factory = new ClientFactory(evernoteAuth);
	    try {
			userStore = factory.createUserStoreClient();
		} catch (TTransportException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    boolean versionOk = false;
		try {
			versionOk = userStore.checkVersion("Evernote EDAMDemo (Java)",
			    com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR,
			    com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
		} catch (TException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    if (!versionOk) {
	      System.err.println("Incompatible Evernote client protocol version");
	      System.exit(1);
	    }
	}
	public EvernoteAuth getEvernoteAuth() {
		return evernoteAuth;
	}
	public ClientFactory getFactory() {
		return factory;
	}
	public UserStoreClient getUserStore() {
		return userStore;
	}

	
	
}
