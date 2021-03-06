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

public class MtPadCommonPageIce extends segimedicaltrans.base.MtConnonPageRsp
{
    public MtPadCommonPageIce()
    {
        super();
        status = "";
        statusName = "";
        exeEndTime = "";
        evaluate = "";
        beginTime = "";
        sourceHouseId = "";
        sourceHouseName = "";
        urgency = "";
        urgencyName = "";
        createBy = "";
        createByName = "";
        createDate = "";
        patientName = "";
        bedNo = "";
    }

    public MtPadCommonPageIce(String taskId, String organId, String organName, String fromHouseId, String fromHouseName, String toHouseId, String toHouseName, String transTypeParentCode, String transTypeParentCodeName, String transTypeId, String transTypeName, String transTools, String transToolsName, String status, String statusName, java.util.List<segiwh.common.User> userList, String exeEndTime, String evaluate, String beginTime, String sourceHouseId, String sourceHouseName, String urgency, String urgencyName, String createBy, String createByName, String createDate, String patientName, String bedNo)
    {
        super(taskId, organId, organName, fromHouseId, fromHouseName, toHouseId, toHouseName, transTypeParentCode, transTypeParentCodeName, transTypeId, transTypeName, transTools, transToolsName);
        this.status = status;
        this.statusName = statusName;
        this.userList = userList;
        this.exeEndTime = exeEndTime;
        this.evaluate = evaluate;
        this.beginTime = beginTime;
        this.sourceHouseId = sourceHouseId;
        this.sourceHouseName = sourceHouseName;
        this.urgency = urgency;
        this.urgencyName = urgencyName;
        this.createBy = createBy;
        this.createByName = createByName;
        this.createDate = createDate;
        this.patientName = patientName;
        this.bedNo = bedNo;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new MtPadCommonPageIce();
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
        "::segi::medicaltrans::mttask::query::MtPadCommonPageIce",
        "::segimedicaltrans::base::MtConnonPageRsp"
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
        __os.writeString(status);
        __os.writeString(statusName);
        segiwh.common.UserListHelper.write(__os, userList);
        __os.writeString(exeEndTime);
        __os.writeString(evaluate);
        __os.writeString(beginTime);
        __os.writeString(sourceHouseId);
        __os.writeString(sourceHouseName);
        __os.writeString(urgency);
        __os.writeString(urgencyName);
        __os.writeString(createBy);
        __os.writeString(createByName);
        __os.writeString(createDate);
        __os.writeString(patientName);
        __os.writeString(bedNo);
        __os.endWriteSlice();
        super.__writeImpl(__os);
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        status = __is.readString();
        statusName = __is.readString();
        userList = segiwh.common.UserListHelper.read(__is);
        exeEndTime = __is.readString();
        evaluate = __is.readString();
        beginTime = __is.readString();
        sourceHouseId = __is.readString();
        sourceHouseName = __is.readString();
        urgency = __is.readString();
        urgencyName = __is.readString();
        createBy = __is.readString();
        createByName = __is.readString();
        createDate = __is.readString();
        patientName = __is.readString();
        bedNo = __is.readString();
        __is.endReadSlice();
        super.__readImpl(__is);
    }

    public String status;

    public String
    getStatus()
    {
        return status;
    }

    public void
    setStatus(String _status)
    {
        status = _status;
    }

    public String statusName;

    public String
    getStatusName()
    {
        return statusName;
    }

    public void
    setStatusName(String _statusName)
    {
        statusName = _statusName;
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

    public String exeEndTime;

    public String
    getExeEndTime()
    {
        return exeEndTime;
    }

    public void
    setExeEndTime(String _exeEndTime)
    {
        exeEndTime = _exeEndTime;
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

    public String beginTime;

    public String
    getBeginTime()
    {
        return beginTime;
    }

    public void
    setBeginTime(String _beginTime)
    {
        beginTime = _beginTime;
    }

    public String sourceHouseId;

    public String
    getSourceHouseId()
    {
        return sourceHouseId;
    }

    public void
    setSourceHouseId(String _sourceHouseId)
    {
        sourceHouseId = _sourceHouseId;
    }

    public String sourceHouseName;

    public String
    getSourceHouseName()
    {
        return sourceHouseName;
    }

    public void
    setSourceHouseName(String _sourceHouseName)
    {
        sourceHouseName = _sourceHouseName;
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

    public String createBy;

    public String
    getCreateBy()
    {
        return createBy;
    }

    public void
    setCreateBy(String _createBy)
    {
        createBy = _createBy;
    }

    public String createByName;

    public String
    getCreateByName()
    {
        return createByName;
    }

    public void
    setCreateByName(String _createByName)
    {
        createByName = _createByName;
    }

    public String createDate;

    public String
    getCreateDate()
    {
        return createDate;
    }

    public void
    setCreateDate(String _createDate)
    {
        createDate = _createDate;
    }

    public String patientName;

    public String
    getPatientName()
    {
        return patientName;
    }

    public void
    setPatientName(String _patientName)
    {
        patientName = _patientName;
    }

    public String bedNo;

    public String
    getBedNo()
    {
        return bedNo;
    }

    public void
    setBedNo(String _bedNo)
    {
        bedNo = _bedNo;
    }

    public MtPadCommonPageIce
    clone()
    {
        return (MtPadCommonPageIce)super.clone();
    }

    public static final long serialVersionUID = 1910045061L;
}
