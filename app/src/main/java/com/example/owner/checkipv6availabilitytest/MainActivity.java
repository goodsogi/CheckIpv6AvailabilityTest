package com.example.owner.checkipv6availabilitytest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.net.Inet6Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isIpv6Available();
    }

    private void isIpv6Available() {

        boolean isAvailable = false;
        try {

            Enumeration networkInterfaces=NetworkInterface.getNetworkInterfaces();
            List networkInterfacesList= Collections.list(networkInterfaces);


            for(Object netInt: networkInterfacesList){
                for(InterfaceAddress address: ((NetworkInterface) netInt).getInterfaceAddresses()){
                    if(address.getAddress() instanceof Inet6Address){
                        // found IPv6 address
                        // do any other validation of address you may need here
                        isAvailable = true;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, isAvailable? "IPv6 사용 가능":"IPv6사용 불가능", Toast.LENGTH_SHORT).show();
    }
}
