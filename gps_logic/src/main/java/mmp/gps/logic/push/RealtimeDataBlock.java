package mmp.gps.logic.push;


import mmp.gps.domain.fault.FaultDto;
import mmp.gps.domain.flow.FlowDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.track.TrackDto;

import java.util.ArrayList;
import java.util.List;

public class RealtimeDataBlock {

    private List tracks;
    private List faults;
    private List flows;
    private List replies;


    public List getTracks() {
        return this.tracks;
    }

    public void setTracks(List tracks) {
        this.tracks = tracks;
    }

    public void add(TrackDto track) {
        if (this.tracks == null) {
            this.tracks = new ArrayList();
        }

        this.tracks.add(track);
    }

    public List getFaults() {
        return this.faults;
    }

    public void setFaults(List faults) {
        this.faults = faults;
    }

    public void add(FaultDto fault) {
        if (this.faults == null) {
            this.faults = new ArrayList();
        }

        this.faults.add(fault);
    }

    public List getFlows() {
        return this.flows;
    }

    public void setFlows(List flows) {
        this.flows = flows;
    }

    public void add(FlowDto flow) {
        if (this.flows == null) {
            this.flows = new ArrayList();
        }

        this.flows.add(flow);
    }

    public List getReplies() {
        return this.replies;
    }

    public void setReplies(List replys) {
        this.replies = replys;
    }

    public void add(InstructResultDto result) {
        if (this.replies == null) {
            this.replies = new ArrayList();
        }

        this.replies.add(result);
    }
}
