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
// Generated from file `mt_personal_volume_month.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.pervolume.month;

public abstract class Callback_PersonalVolumeMonthServiceIce_getMonthTransVolumeRank
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<segi.medicaltrans.report.pervolume.month.PersonalVolumeMonthPaginator>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        PersonalVolumeMonthServiceIcePrxHelper.__getMonthTransVolumeRank_completed(this, __result);
    }
}
