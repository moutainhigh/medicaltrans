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
// Generated from file `mt_his_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mthistask.query;

public final class TaskExeInfoListHelper
{
    public static void
    write(IceInternal.BasicStream __os, java.util.List<FixedTaskHisExeInfoIce> __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.size());
            for(FixedTaskHisExeInfoIce __elem : __v)
            {
                FixedTaskHisExeInfoIce.__write(__os, __elem);
            }
        }
    }

    public static java.util.List<FixedTaskHisExeInfoIce>
    read(IceInternal.BasicStream __is)
    {
        java.util.List<FixedTaskHisExeInfoIce> __v;
        __v = new java.util.ArrayList();
        final int __len0 = __is.readAndCheckSeqSize(8);
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            FixedTaskHisExeInfoIce __elem = null;
            __elem = FixedTaskHisExeInfoIce.__read(__is, __elem);
            __v.add(__elem);
        }
        return __v;
    }
}
