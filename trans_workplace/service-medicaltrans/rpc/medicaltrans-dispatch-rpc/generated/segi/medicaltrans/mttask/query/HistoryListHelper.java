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

public final class HistoryListHelper
{
    public static void
    write(IceInternal.BasicStream __os, java.util.List<MtTaskHistoryRerurnIce> __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.size());
            for(MtTaskHistoryRerurnIce __elem : __v)
            {
                MtTaskHistoryRerurnIce.__write(__os, __elem);
            }
        }
    }

    public static java.util.List<MtTaskHistoryRerurnIce>
    read(IceInternal.BasicStream __is)
    {
        java.util.List<MtTaskHistoryRerurnIce> __v;
        __v = new java.util.ArrayList();
        final int __len0 = __is.readAndCheckSeqSize(17);
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            MtTaskHistoryRerurnIce __elem = null;
            __elem = MtTaskHistoryRerurnIce.__read(__is, __elem);
            __v.add(__elem);
        }
        return __v;
    }
}
