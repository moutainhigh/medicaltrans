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
// Generated from file `commons.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package page;

public class RpcPageIce extends Ice.ObjectImpl
{
    public RpcPageIce()
    {
        pageLength = "";
        pageNo = "";
        totalCount = "";
    }

    public RpcPageIce(String pageLength, String pageNo, String totalCount)
    {
        this.pageLength = pageLength;
        this.pageNo = pageNo;
        this.totalCount = totalCount;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new RpcPageIce();
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
        "::page::RpcPageIce"
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
        __os.writeString(pageLength);
        __os.writeString(pageNo);
        __os.writeString(totalCount);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        pageLength = __is.readString();
        pageNo = __is.readString();
        totalCount = __is.readString();
        __is.endReadSlice();
    }

    public String pageLength;

    public String
    getPageLength()
    {
        return pageLength;
    }

    public void
    setPageLength(String _pageLength)
    {
        pageLength = _pageLength;
    }

    public String pageNo;

    public String
    getPageNo()
    {
        return pageNo;
    }

    public void
    setPageNo(String _pageNo)
    {
        pageNo = _pageNo;
    }

    public String totalCount;

    public String
    getTotalCount()
    {
        return totalCount;
    }

    public void
    setTotalCount(String _totalCount)
    {
        totalCount = _totalCount;
    }

    public RpcPageIce
    clone()
    {
        return (RpcPageIce)super.clone();
    }

    public static final long serialVersionUID = 1994107524L;
}
