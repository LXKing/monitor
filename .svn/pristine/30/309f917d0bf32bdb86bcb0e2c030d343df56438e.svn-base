package com.rayton.gps.tag;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

public class ExtHtmlTagRuleBundle implements TagRuleBundle {

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

    }

    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        ExportTagToContentRule leftpad = new ExportTagToContentRule(siteMeshContext, contentProperty.getChild
                ("leftpad"), false);
        defaultState.addRule("leftpad", leftpad);
        ExportTagToContentRule rightpad = new ExportTagToContentRule(siteMeshContext, contentProperty.getChild
                ("rightpad"), false);
        defaultState.addRule("rightpad", rightpad);
    }
}
