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
// Generated from file `medicaltrans_base_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segimedicaltrans.basecommon;

public class MtTaskBaseRsp extends Ice.ObjectImpl
{
    public MtTaskBaseRsp()
    {
        taskId = "";
        organId = "";
        organName = "";
        transTypeParentCode = "";
        transTypeParentCodeName = "";
        transTypeId = "";
        transTypeName = "";
        fromHouseId = "";
        fromHouseName = "";
        toHouseId = "";
        toHouseName = "";
        patientName = "";
        bedNo = "";
        patientGender = "";
        patientGenderName = "";
        medicalRecNo = "";
        transTools = "";
        transToolsName = "";
        taskContent = "";
        beginTime = "";
        endTime = "";
        exeBeginTime = "";
        exeEndTime = "";
        status = "";
        statusName = "";
        fromHouseLocateType = "";
        fromHouseMac = "";
        toHouseLocateType = "";
        toHouseMac = "";
        exeEndUserId = "";
    }

    public MtTaskBaseRsp(String taskId, String organId, String organName, String transTypeParentCode, String transTypeParentCodeName, String transTypeId, String transTypeName, String fromHouseId, String fromHouseName, String toHouseId, String toHouseName, String patientName, String bedNo, String patientGender, String patientGenderName, String medicalRecNo, String transTools, String transToolsName, String taskContent, String beginTime, String endTime, String exeBeginTime, String exeEndTime, String status, String statusName, String fromHouseLocateType, String fromHouseMac, String toHouseLocateType, String toHouseMac, String exeEndUserId)
    {
        this.taskId = taskId;
        this.organId = organId;
        this.organName = organName;
        this.transTypeParentCode = transTypeParentCode;
        this.transTypeParentCodeName = transTypeParentCodeName;
        this.transTypeId = transTypeId;
        this.transTypeName = transTypeName;
        this.fromHouseId = fromHouseId;
        this.fromHouseName = fromHouseName;
        this.toHouseId = toHouseId;
        this.toHouseName = toHouseName;
        this.patientName = patientName;
        this.bedNo = bedNo;
        this.patientGender = patientGender;
        this.patientGenderName = patientGenderName;
        this.medicalRecNo = medicalRecNo;
        this.transTools = transTools;
        this.transToolsName = transToolsName;
        this.taskContent = taskContent;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.exeBeginTime = exeBeginTime;
        this.exeEndTime = exeEndTime;
        this.status = status;
        this.statusName = statusName;
        this.fromHouseLocateType = fromHouseLocateType;
        this.fromHouseMac = fromHouseMac;
        this.toHouseLocateType = toHouseLocateType;
        this.toHouseMac = toHouseMac;
        this.exeEndUserId = exeEndUserId;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new MtTaskBaseRsp();
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
        "::segimedicaltrans::basecommon::MtTaskBaseRsp"
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
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.writeString(taskId);
        __os.writeString(organId);
        __os.writeString(organName);
        __os.writeString(transTypeParentCode);
        __os.writeString(transTypeParentCodeName);
        __os.writeString(transTypeId);
        __os.writeString(transTypeName);
        __os.writeString(fromHouseId);
        __os.writeString(fromHouseName);
        __os.writeString(toHouseId);
        __os.writeString(toHouseName);
        __os.writeString(patientName);
        __os.writeString(bedNo);
        __os.writeString(patientGender);
        __os.writeString(patientGenderName);
        __os.writeString(medicalRecNo);
        __os.writeString(transTools);
        __os.writeString(transToolsName);
        __os.writeString(taskContent);
        __os.writeString(beginTime);
        __os.writeString(endTime);
        __os.writeString(exeBeginTime);
        __os.writeString(exeEndTime);
        __os.writeString(status);
        __os.writeString(statusName);
        __os.writeString(fromHouseLocateType);
        __os.writeString(fromHouseMac);
        __os.writeString(toHouseLocateType);
        __os.writeString(toHouseMac);
        __os.writeString(exeEndUserId);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        taskId = __is.readString();
        organId = __is.readString();
        organName = __is.readString();
        transTypeParentCode = __is.readString();
        transTypeParentCodeName = __is.readString();
        transTypeId = __is.readString();
        transTypeName = __is.readString();
        fromHouseId = __is.readString();
        fromHouseName = __is.readString();
        toHouseId = __is.readString();
        toHouseName = __is.readString();
        patientName = __is.readString();
        bedNo = __is.readString();
        patientGender = __is.readString();
        patientGenderName = __is.readString();
        medicalRecNo = __is.readString();
        transTools = __is.readString();
        transToolsName = __is.readString();
        taskContent = __is.readString();
        beginTime = __is.readString();
        endTime = __is.readString();
        exeBeginTime = __is.readString();
        exeEndTime = __is.readString();
        status = __is.readString();
        statusName = __is.readString();
        fromHouseLocateType = __is.readString();
        fromHouseMac = __is.readString();
        toHouseLocateType = __is.readString();
        toHouseMac = __is.readString();
        exeEndUserId = __is.readString();
        __is.endReadSlice();
    }

    public String taskId;

    public String
    getTaskId()
    {
        return taskId;
    }

    public void
    setTaskId(String _taskId)
    {
        taskId = _taskId;
    }

    public String organId;

    public String
    getOrganId()
    {
        return organId;
    }

    public void
    setOrganId(String _organId)
    {
        organId = _organId;
    }

    public String organName;

    public String
    getOrganName()
    {
        return organName;
    }

    public void
    setOrganName(String _organName)
    {
        organName = _organName;
    }

    public String transTypeParentCode;

    public String
    getTransTypeParentCode()
    {
        return transTypeParentCode;
    }

    public void
    setTransTypeParentCode(String _transTypeParentCode)
    {
        transTypeParentCode = _transTypeParentCode;
    }

    public String transTypeParentCodeName;

    public String
    getTransTypeParentCodeName()
    {
        return transTypeParentCodeName;
    }

    public void
    setTransTypeParentCodeName(String _transTypeParentCodeName)
    {
        transTypeParentCodeName = _transTypeParentCodeName;
    }

    public String transTypeId;

    public String
    getTransTypeId()
    {
        return transTypeId;
    }

    public void
    setTransTypeId(String _transTypeId)
    {
        transTypeId = _transTypeId;
    }

    public String transTypeName;

    public String
    getTransTypeName()
    {
        return transTypeName;
    }

    public void
    setTransTypeName(String _transTypeName)
    {
        transTypeName = _transTypeName;
    }

    public String fromHouseId;

    public String
    getFromHouseId()
    {
        return fromHouseId;
    }

    public void
    setFromHouseId(String _fromHouseId)
    {
        fromHouseId = _fromHouseId;
    }

    public String fromHouseName;

    public String
    getFromHouseName()
    {
        return fromHouseName;
    }

    public void
    setFromHouseName(String _fromHouseName)
    {
        fromHouseName = _fromHouseName;
    }

    public String toHouseId;

    public String
    getToHouseId()
    {
        return toHouseId;
    }

    public void
    setToHouseId(String _toHouseId)
    {
        toHouseId = _toHouseId;
    }

    public String toHouseName;

    public String
    getToHouseName()
    {
        return toHouseName;
    }

    public void
    setToHouseName(String _toHouseName)
    {
        toHouseName = _toHouseName;
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

    public String patientGender;

    public String
    getPatientGender()
    {
        return patientGender;
    }

    public void
    setPatientGender(String _patientGender)
    {
        patientGender = _patientGender;
    }

    public String patientGenderName;

    public String
    getPatientGenderName()
    {
        return patientGenderName;
    }

    public void
    setPatientGenderName(String _patientGenderName)
    {
        patientGenderName = _patientGenderName;
    }

    public String medicalRecNo;

    public String
    getMedicalRecNo()
    {
        return medicalRecNo;
    }

    public void
    setMedicalRecNo(String _medicalRecNo)
    {
        medicalRecNo = _medicalRecNo;
    }

    public String transTools;

    public String
    getTransTools()
    {
        return transTools;
    }

    public void
    setTransTools(String _transTools)
    {
        transTools = _transTools;
    }

    public String transToolsName;

    public String
    getTransToolsName()
    {
        return transToolsName;
    }

    public void
    setTransToolsName(String _transToolsName)
    {
        transToolsName = _transToolsName;
    }

    public String taskContent;

    public String
    getTaskContent()
    {
        return taskContent;
    }

    public void
    setTaskContent(String _taskContent)
    {
        taskContent = _taskContent;
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

    public String endTime;

    public String
    getEndTime()
    {
        return endTime;
    }

    public void
    setEndTime(String _endTime)
    {
        endTime = _endTime;
    }

    public String exeBeginTime;

    public String
    getExeBeginTime()
    {
        return exeBeginTime;
    }

    public void
    setExeBeginTime(String _exeBeginTime)
    {
        exeBeginTime = _exeBeginTime;
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

    public String fromHouseLocateType;

    public String
    getFromHouseLocateType()
    {
        return fromHouseLocateType;
    }

    public void
    setFromHouseLocateType(String _fromHouseLocateType)
    {
        fromHouseLocateType = _fromHouseLocateType;
    }

    public String fromHouseMac;

    public String
    getFromHouseMac()
    {
        return fromHouseMac;
    }

    public void
    setFromHouseMac(String _fromHouseMac)
    {
        fromHouseMac = _fromHouseMac;
    }

    public String toHouseLocateType;

    public String
    getToHouseLocateType()
    {
        return toHouseLocateType;
    }

    public void
    setToHouseLocateType(String _toHouseLocateType)
    {
        toHouseLocateType = _toHouseLocateType;
    }

    public String toHouseMac;

    public String
    getToHouseMac()
    {
        return toHouseMac;
    }

    public void
    setToHouseMac(String _toHouseMac)
    {
        toHouseMac = _toHouseMac;
    }

    public String exeEndUserId;

    public String
    getExeEndUserId()
    {
        return exeEndUserId;
    }

    public void
    setExeEndUserId(String _exeEndUserId)
    {
        exeEndUserId = _exeEndUserId;
    }

    public MtTaskBaseRsp
    clone()
    {
        return (MtTaskBaseRsp)super.clone();
    }

    public static final long serialVersionUID = 824468535L;
}
