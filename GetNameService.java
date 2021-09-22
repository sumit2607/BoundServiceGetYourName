package com.example.boundservicegetyourname;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class GetNameService extends Service {
    public GetNameService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }
    public String getName(){
return "Sumit Rai";
    }
    public class ServiceBinder extends Binder {
    public GetNameService getNameService(){
        return GetNameService.this;
    }
    }
}