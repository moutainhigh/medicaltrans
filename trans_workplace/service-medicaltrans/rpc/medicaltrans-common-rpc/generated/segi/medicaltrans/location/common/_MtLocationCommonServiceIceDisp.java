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
// Generated from file `mt_location_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.location.common;

public abstract class _MtLocationCommonServiceIceDisp extends Ice.ObjectImpl implements MtLocationCommonServiceIce
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::segi::medicaltrans::location::common::MtLocationCommonServiceIce"
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

    public final LocationInfoReturnIce getLocationInfoByMac(int organId, String mac)
    {
        return getLocationInfoByMac(organId, mac, null);
    }

    public final LocationInfoListReturnIce getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList)
    {
        return getLocationInfoByRefIdList(organId, locationIdList, null);
    }

    public final LocationInfoListReturnIce getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList)
    {
        return getLocationInfoBylocationIdList(locationIdList, null);
    }

    public final LocationInfoReturn queryLocationInfo(int organId, int locationId)
    {
        return queryLocationInfo(organId, locationId, null);
    }

    public final LocationInfoListReturn queryLocationInfoByOrganIdList(int organId)
    {
        return queryLocationInfoByOrganIdList(organId, null);
    }

    public final LocationInfoListReturnIce queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList)
    {
        return queryLocationInfoListByOrganId(organId, locationIdList, null);
    }

    public final LocationInfoListReturn queryLocationList(java.util.List<java.lang.Integer> locationIdList)
    {
        return queryLocationList(locationIdList, null);
    }

    public final resp.RpcRespIce refreshRedisLocaiton()
    {
        return refreshRedisLocaiton(null);
    }

    public final resp.RpcRespIce refreshRedisUserLocaiton()
    {
        return refreshRedisUserLocaiton(null);
    }

    public static Ice.DispatchStatus ___getLocationInfoByRefIdList(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int organId;
        java.util.List<java.lang.Integer> locationIdList;
        organId = __is.readInt();
        locationIdList = LocationIdListHelper.read(__is);
        __inS.endReadParams();
        LocationInfoListReturnIce __ret = __obj.getLocationInfoByRefIdList(organId, locationIdList, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoListReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getLocationInfoBylocationIdList(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        java.util.List<java.lang.Integer> locationIdList;
        locationIdList = LocationIdListHelper.read(__is);
        __inS.endReadParams();
        LocationInfoListReturnIce __ret = __obj.getLocationInfoBylocationIdList(locationIdList, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoListReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getLocationInfoByMac(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int organId;
        String mac;
        organId = __is.readInt();
        mac = __is.readString();
        __inS.endReadParams();
        LocationInfoReturnIce __ret = __obj.getLocationInfoByMac(organId, mac, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryLocationInfoByOrganIdList(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int organId;
        organId = __is.readInt();
        __inS.endReadParams();
        LocationInfoListReturn __ret = __obj.queryLocationInfoByOrganIdList(organId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoListReturn.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryLocationInfo(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int organId;
        int locationId;
        organId = __is.readInt();
        locationId = __is.readInt();
        __inS.endReadParams();
        LocationInfoReturn __ret = __obj.queryLocationInfo(organId, locationId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoReturn.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryLocationList(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        java.util.List<java.lang.Integer> locationIdList;
        locationIdList = LocationIdListHelper.read(__is);
        __inS.endReadParams();
        LocationInfoListReturn __ret = __obj.queryLocationList(locationIdList, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoListReturn.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryLocationInfoListByOrganId(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int organId;
        java.util.List<java.lang.Integer> locationIdList;
        organId = __is.readInt();
        locationIdList = LocationIdListHelper.read(__is);
        __inS.endReadParams();
        LocationInfoListReturnIce __ret = __obj.queryLocationInfoListByOrganId(organId, locationIdList, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        LocationInfoListReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___refreshRedisLocaiton(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.readEmptyParams();
        resp.RpcRespIce __ret = __obj.refreshRedisLocaiton(__current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___refreshRedisUserLocaiton(MtLocationCommonServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.readEmptyParams();
        resp.RpcRespIce __ret = __obj.refreshRedisUserLocaiton(__current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "getLocationInfoByMac",
        "getLocationInfoByRefIdList",
        "getLocationInfoBylocationIdList",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "queryLocationInfo",
        "queryLocationInfoByOrganIdList",
        "queryLocationInfoListByOrganId",
        "queryLocationList",
        "refreshRedisLocaiton",
        "refreshRedisUserLocaiton"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___getLocationInfoByMac(this, in, __current);
            }
            case 1:
            {
                return ___getLocationInfoByRefIdList(this, in, __current);
            }
            case 2:
            {
                return ___getLocationInfoBylocationIdList(this, in, __current);
            }
            case 3:
            {
                return ___ice_id(this, in, __current);
            }
            case 4:
            {
                return ___ice_ids(this, in, __current);
            }
            case 5:
            {
                return ___ice_isA(this, in, __current);
            }
            case 6:
            {
                return ___ice_ping(this, in, __current);
            }
            case 7:
            {
                return ___queryLocationInfo(this, in, __current);
            }
            case 8:
            {
                return ___queryLocationInfoByOrganIdList(this, in, __current);
            }
            case 9:
            {
                return ___queryLocationInfoListByOrganId(this, in, __current);
            }
            case 10:
            {
                return ___queryLocationList(this, in, __current);
            }
            case 11:
            {
                return ___refreshRedisLocaiton(this, in, __current);
            }
            case 12:
            {
                return ___refreshRedisUserLocaiton(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}
