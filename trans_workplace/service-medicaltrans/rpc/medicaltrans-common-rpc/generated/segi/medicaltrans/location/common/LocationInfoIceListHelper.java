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

public final class LocationInfoIceListHelper
{
    public static void
    write(IceInternal.BasicStream __os, java.util.List<LocationInfoIce> __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.size());
            for(LocationInfoIce __elem : __v)
            {
                LocationInfoIce.__write(__os, __elem);
            }
        }
    }

    public static java.util.List<LocationInfoIce>
    read(IceInternal.BasicStream __is)
    {
        java.util.List<LocationInfoIce> __v;
        __v = new java.util.ArrayList();
        final int __len0 = __is.readAndCheckSeqSize(8);
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            LocationInfoIce __elem = null;
            __elem = LocationInfoIce.__read(__is, __elem);
            __v.add(__elem);
        }
        return __v;
    }
}