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
// Generated from file `spacemanage.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.spacemanage;

public abstract class _SpaceManageServiceIceDisp extends Ice.ObjectImpl implements SpaceManageServiceIce
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
        "::segi::medicaltrans::base::spacemanage::SpaceManageServiceIce"
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

    public final BuildDetailReturnIce getBuildDetail(String buildId)
    {
        return getBuildDetail(buildId, null);
    }

    public final BuildSimpleReturnIce getBuildSimpleInfo(String buildId)
    {
        return getBuildSimpleInfo(buildId, null);
    }

    public final FloorDetailReturnIce getFloorDetail(String floorId)
    {
        return getFloorDetail(floorId, null);
    }

    public final FloorSimpleReturnIce getFloorInfoByFloorId(String floorId)
    {
        return getFloorInfoByFloorId(floorId, null);
    }

    public final HouseInfoReturnIce getHouseAll(HouseInfoIce houseInfoIce)
    {
        return getHouseAll(houseInfoIce, null);
    }

    public final UnitDetailReturnIce getUnitDetail(String unitId)
    {
        return getUnitDetail(unitId, null);
    }

    public final UnitSimpleReturnIce getUnitSimpleInfo(String unitId)
    {
        return getUnitSimpleInfo(unitId, null);
    }

    public final BuildSimpleRetIce queryBuildByCommID(String communityId)
    {
        return queryBuildByCommID(communityId, null);
    }

    public final PositionRetIce queryPositionListByParId(String communityId, String parId, String type)
    {
        return queryPositionListByParId(communityId, parId, type, null);
    }

    public final PositionRetIce queryPositionListByParIdInd(String communityId, String parId, String type)
    {
        return queryPositionListByParIdInd(communityId, parId, type, null);
    }

    public static Ice.DispatchStatus ___queryBuildByCommID(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String communityId;
        communityId = __is.readString();
        __inS.endReadParams();
        BuildSimpleRetIce __ret = __obj.queryBuildByCommID(communityId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        BuildSimpleRetIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getBuildSimpleInfo(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String buildId;
        buildId = __is.readString();
        __inS.endReadParams();
        BuildSimpleReturnIce __ret = __obj.getBuildSimpleInfo(buildId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        BuildSimpleReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getUnitSimpleInfo(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String unitId;
        unitId = __is.readString();
        __inS.endReadParams();
        UnitSimpleReturnIce __ret = __obj.getUnitSimpleInfo(unitId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        UnitSimpleReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getFloorInfoByFloorId(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String floorId;
        floorId = __is.readString();
        __inS.endReadParams();
        FloorSimpleReturnIce __ret = __obj.getFloorInfoByFloorId(floorId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        FloorSimpleReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryPositionListByParId(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String communityId;
        String parId;
        String type;
        communityId = __is.readString();
        parId = __is.readString();
        type = __is.readString();
        __inS.endReadParams();
        PositionRetIce __ret = __obj.queryPositionListByParId(communityId, parId, type, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        PositionRetIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getBuildDetail(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String buildId;
        buildId = __is.readString();
        __inS.endReadParams();
        BuildDetailReturnIce __ret = __obj.getBuildDetail(buildId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        BuildDetailReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getUnitDetail(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String unitId;
        unitId = __is.readString();
        __inS.endReadParams();
        UnitDetailReturnIce __ret = __obj.getUnitDetail(unitId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        UnitDetailReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getFloorDetail(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String floorId;
        floorId = __is.readString();
        __inS.endReadParams();
        FloorDetailReturnIce __ret = __obj.getFloorDetail(floorId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        FloorDetailReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getHouseAll(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        HouseInfoIce houseInfoIce = null;
        houseInfoIce = HouseInfoIce.__read(__is, houseInfoIce);
        __inS.endReadParams();
        HouseInfoReturnIce __ret = __obj.getHouseAll(houseInfoIce, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        HouseInfoReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryPositionListByParIdInd(SpaceManageServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String communityId;
        String parId;
        String type;
        communityId = __is.readString();
        parId = __is.readString();
        type = __is.readString();
        __inS.endReadParams();
        PositionRetIce __ret = __obj.queryPositionListByParIdInd(communityId, parId, type, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        PositionRetIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "getBuildDetail",
        "getBuildSimpleInfo",
        "getFloorDetail",
        "getFloorInfoByFloorId",
        "getHouseAll",
        "getUnitDetail",
        "getUnitSimpleInfo",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "queryBuildByCommID",
        "queryPositionListByParId",
        "queryPositionListByParIdInd"
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
                return ___getBuildDetail(this, in, __current);
            }
            case 1:
            {
                return ___getBuildSimpleInfo(this, in, __current);
            }
            case 2:
            {
                return ___getFloorDetail(this, in, __current);
            }
            case 3:
            {
                return ___getFloorInfoByFloorId(this, in, __current);
            }
            case 4:
            {
                return ___getHouseAll(this, in, __current);
            }
            case 5:
            {
                return ___getUnitDetail(this, in, __current);
            }
            case 6:
            {
                return ___getUnitSimpleInfo(this, in, __current);
            }
            case 7:
            {
                return ___ice_id(this, in, __current);
            }
            case 8:
            {
                return ___ice_ids(this, in, __current);
            }
            case 9:
            {
                return ___ice_isA(this, in, __current);
            }
            case 10:
            {
                return ___ice_ping(this, in, __current);
            }
            case 11:
            {
                return ___queryBuildByCommID(this, in, __current);
            }
            case 12:
            {
                return ___queryPositionListByParId(this, in, __current);
            }
            case 13:
            {
                return ___queryPositionListByParIdInd(this, in, __current);
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
