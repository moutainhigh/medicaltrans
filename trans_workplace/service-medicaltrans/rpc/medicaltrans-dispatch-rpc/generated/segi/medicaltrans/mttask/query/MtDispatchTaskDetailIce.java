// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `mt_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.query;

public class MtDispatchTaskDetailIce extends segimedicaltrans.base.MtTaskBaseRsp
{
    public MtDispatchTaskDetailIce()
    {
        super();
        limitMinute = "";
        finishContent = "";
        evaluate = "";
        evaluateContent = "";
        routeId = "";
        urgency = "";
        urgencyName = "";
        transPersonCount = "";
        receiver = "";
        receiverUserName = "";
        receiverHouseName = "";
        receiveTime = "";
    }

    public MtDispatchTaskDetailIce(String taskId, String organId, String organName, String transTypeParentCode, String transTypeParentCodeName, String transTypeId, String transTypeName, String fromHouseId, String fromHouseName, String toHouseId, String toHouseName, String patientName, String bedNo, String patientGender, String patientGenderName, String medicalRecNo, String transTools, String transToolsName, String taskContent, String beginTime, String endTime, String exeBeginTime, String exeEndTime, String status, String statusName, String fromHouseLocateType, String fromHouseMac, String toHouseLocateType, String toHouseMac, String exeEndUserId, String sourceHouseId, String sourceHouseName, String limitMinute, String finishContent, String evaluate, String evaluateContent, String routeId, java.util.List<segiwh.common.User> userList, String urgency, String urgencyName, String transPersonCount, String receiver, String receiverUserName, String receiverHouseName, String receiveTime)
    {
        super(taskId, organId, organName, transTypeParentCode, transTypeParentCodeName, transTypeId, transTypeName, fromHouseId, fromHouseName, toHouseId, toHouseName, patientName, bedNo, patientGender, patientGenderName, medicalRecNo, transTools, transToolsName, taskContent, beginTime, endTime, exeBeginTime, exeEndTime, status, statusName, fromHouseLocateType, fromHouseMac, toHouseLocateType, toHouseMac, exeEndUserId, sourceHouseId, sourceHouseName);
        this.limitMinute = limitMinute;
        this.finishContent = finishContent;
        this.evaluate = evaluate;
        this.evaluateContent = evaluateContent;
        this.routeId = routeId;
        this.userList = userList;
        this.urgency = urgency;
        this.urgencyName = urgencyName;
        this.transPersonCount = transPersonCount;
        this.receiver = receiver;
        this.receiverUserName = receiverUserName;
        this.receiverHouseName = receiverHouseName;
        this.receiveTime = receiveTime;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new MtDispatchTaskDetailIce();
        }

        public void destroy()
        {
        }
    }
    private static Ice.ObjectFactory _factory = new __F();

    public static Ice.ObjectFactory
    ice_factory()
    {
        return _factory;
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::segi::medicaltrans::mttask::query::MtDispatchTaskDetailIce",
        "::segimedicaltrans::base::MtTaskBaseRsp"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[1];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String ice_staticId()
    {
        return __ids[1];
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, false);
        __os.writeString(limitMinute);
        __os.writeString(finishContent);
        __os.writeString(evaluate);
        __os.writeString(evaluateContent);
        __os.writeString(routeId);
        segiwh.common.UserListHelper.write(__os, userList);
        __os.writeString(urgency);
        __os.writeString(urgencyName);
        __os.writeString(transPersonCount);
        __os.writeString(receiver);
        __os.writeString(receiverUserName);
        __os.writeString(receiverHouseName);
        __os.writeString(receiveTime);
        __os.endWriteSlice();
        super.__writeImpl(__os);
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        limitMinute = __is.readString();
        finishContent = __is.readString();
        evaluate = __is.readString();
        evaluateContent = __is.readString();
        routeId = __is.readString();
        userList = segiwh.common.UserListHelper.read(__is);
        urgency = __is.readString();
        urgencyName = __is.readString();
        transPersonCount = __is.readString();
        receiver = __is.readString();
        receiverUserName = __is.readString();
        receiverHouseName = __is.readString();
        receiveTime = __is.readString();
        __is.endReadSlice();
        super.__readImpl(__is);
    }

    public String limitMinute;

    public String
    getLimitMinute()
    {
        return limitMinute;
    }

    public void
    setLimitMinute(String _limitMinute)
    {
        limitMinute = _limitMinute;
    }

    public String finishContent;

    public String
    getFinishContent()
    {
        return finishContent;
    }

    public void
    setFinishContent(String _finishContent)
    {
        finishContent = _finishContent;
    }

    public String evaluate;

    public String
    getEvaluate()
    {
        return evaluate;
    }

    public void
    setEvaluate(String _evaluate)
    {
        evaluate = _evaluate;
    }

    public String evaluateContent;

    public String
    getEvaluateContent()
    {
        return evaluateContent;
    }

    public void
    setEvaluateContent(String _evaluateContent)
    {
        evaluateContent = _evaluateContent;
    }

    public String routeId;

    public String
    getRouteId()
    {
        return routeId;
    }

    public void
    setRouteId(String _routeId)
    {
        routeId = _routeId;
    }

    public java.util.List<segiwh.common.User> userList;

    public java.util.List<segiwh.common.User>
    getUserList()
    {
        return userList;
    }

    public void
    setUserList(java.util.List<segiwh.common.User> _userList)
    {
        userList = _userList;
    }

    public String urgency;

    public String
    getUrgency()
    {
        return urgency;
    }

    public void
    setUrgency(String _urgency)
    {
        urgency = _urgency;
    }

    public String urgencyName;

    public String
    getUrgencyName()
    {
        return urgencyName;
    }

    public void
    setUrgencyName(String _urgencyName)
    {
        urgencyName = _urgencyName;
    }

    public String transPersonCount;

    public String
    getTransPersonCount()
    {
        return transPersonCount;
    }

    public void
    setTransPersonCount(String _transPersonCount)
    {
        transPersonCount = _transPersonCount;
    }

    public String receiver;

    public String
    getReceiver()
    {
        return receiver;
    }

    public void
    setReceiver(String _receiver)
    {
        receiver = _receiver;
    }

    public String receiverUserName;

    public String
    getReceiverUserName()
    {
        return receiverUserName;
    }

    public void
    setReceiverUserName(String _receiverUserName)
    {
        receiverUserName = _receiverUserName;
    }

    public String receiverHouseName;

    public String
    getReceiverHouseName()
    {
        return receiverHouseName;
    }

    public void
    setReceiverHouseName(String _receiverHouseName)
    {
        receiverHouseName = _receiverHouseName;
    }

    public String receiveTime;

    public String
    getReceiveTime()
    {
        return receiveTime;
    }

    public void
    setReceiveTime(String _receiveTime)
    {
        receiveTime = _receiveTime;
    }

    public MtDispatchTaskDetailIce
    clone()
    {
        return (MtDispatchTaskDetailIce)super.clone();
    }

    public static final long serialVersionUID = -67130431L;
}