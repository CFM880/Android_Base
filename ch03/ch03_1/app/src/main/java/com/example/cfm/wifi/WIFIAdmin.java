package com.example.cfm.wifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by cfm on 15-12-14.
 */
public class WIFIAdmin {
    private WifiManager mWifiManager = null;
    private WifiInfo mWifiInfo = null;
    private List<ScanResult> mWifiList = null;
    private List<WifiConfiguration> mWifiConfigurations = null;
    private WifiManager.WifiLock mwifiLock = null;

    public WIFIAdmin(Context context) {
        mWifiManager = (WifiManager)
                context.getSystemService(context.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
    }

    public void OpenWifi(){
        if (!mWifiManager.isWifiEnabled()){
            mWifiManager.setWifiEnabled(true);
        }
    }

    public void CloseWifi(){
        if (mWifiManager.isWifiEnabled()){
            mWifiManager.setWifiEnabled(false);
        }
    }

    public void lockWifi(){
        mwifiLock.acquire();
    }

    public void rlockWifi(){
        if (mwifiLock.isHeld()){
            mwifiLock.acquire();
        }
    }

    public void CreateWifi(){
        mwifiLock = mWifiManager.createWifiLock("Testss");
    }

    public List<WifiConfiguration> GetConfiguration(){
        return mWifiConfigurations;
    }

    public void ConnectConfiguration(int index){
        if (index>mWifiConfigurations.size()){
            return;
        }
        mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId,true);
    }

    public void StartScan(){
        mWifiManager.startScan();
        mWifiList = mWifiManager.getScanResults();
        mWifiConfigurations = mWifiManager.getConfiguredNetworks();
    }

    public List<ScanResult> GetWifiList(){
        return mWifiList;
    }

    public StringBuilder LookUpScan(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mWifiList.size(); i++){
            stringBuilder.append("Index" + new Integer(i + 1).toString() + ":");

            stringBuilder.append(mWifiList.get(i)).toString();
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }


    public String GatMacAddress(){
        return (mWifiInfo == null)?"NULL":mWifiInfo.getMacAddress();
    }

    public String GetBSSID(){
        return (mWifiInfo == null)?"NULL":mWifiInfo.getBSSID();
    }

    public int GetIpAddress(){
        return (mWifiInfo == null)?0:mWifiInfo.getIpAddress();
    }

    //得到连接的ID
    public int GetNetworkId()
    {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
    }
    //得到WifiInfo的所有信息包
    public String GetWifiInfo()
    {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
    }
    //添加一个网络并连接
    public void AddNetwork(WifiConfiguration wcg)
    {
        int wcgID = mWifiManager.addNetwork(wcg);
        mWifiManager.enableNetwork(wcgID, true);
    }
    //断开指定ID的网络
    public void DisconnectWifi(int netId)
    {
        mWifiManager.disableNetwork(netId);
        mWifiManager.disconnect();
    }

}
