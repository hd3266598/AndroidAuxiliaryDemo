package com.example.hd.androidauxiliarydemo

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.ServiceConnection
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo


/**
 * @author HuangDa
 * @date 2018/8/20
 * @description
 */

class AccessibilitySampleService : AccessibilityService() {
    override fun onCreate() {
        super.onCreate()
        serviceInfo.flags = AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE
    }

    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        if (p0?.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            val nodeInfo = rootInActiveWindow
            dfsNode(nodeInfo, 0)
        }
    }


    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.e(this.javaClass.simpleName, "吱吱吱~达达服务启动~")
    }


    override fun onInterrupt() {
    }

    override fun unbindService(conn: ServiceConnection?) {
        super.unbindService(conn)
        Log.e(this.javaClass.simpleName, "吱吱吱~达达服务关闭~")
    }

    private fun dfsNode(node: AccessibilityNodeInfo, num: Int) {
        val stringBuilder = StringBuilder()
        for (i in 0 until num) {
            stringBuilder.append("__ ")    //父子节点之间的缩进
        }
        Log.i("####", stringBuilder.toString() + node.toString())   //打印
        for (i in 0 until node.childCount) {      //遍历子节点
            dfsNode(node.getChild(i), num + 1)
        }
    }
}