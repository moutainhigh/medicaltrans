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
// Generated from file `mt_trans_statistics.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.trans;

public interface _TransStatisticsServiceIceOperations
{
    TransTimeReturnIce queryTransportTime(int organId, Ice.Current __current);

    TransRatioReturnIce queryTaskStructureRatio(int organId, Ice.Current __current);

    TransRatioReturnIce queryTransModeRatio(int organId, Ice.Current __current);

    TransRatioReturnIce queryFromHouseRatio(int organId, Ice.Current __current);
}