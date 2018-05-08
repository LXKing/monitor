package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.dictionary.DictionaryItem;
import com.rayton.gps.dao.baseinfo.dictionary.DictionaryItemInfo;
import com.rayton.gps.service.DictionaryService;
import com.rayton.gps.util.ResponseEntityWrapper;
import com.rayton.gps.util.enums.DictionaryKinds;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;


    @RequiresPermissions("baseinfo.dictionary")
    @Log(name = "打开数据字典页面")
    @GetMapping(value = "/dictionary/dictionary.iframe")
    public String index(Model model) {
        model.addAttribute("dictionaryKinds", DictionaryKinds.values()); model.addAttribute("txtDictionaryKind", 1);
        return "/baseinfo/dictionary/dictionary.iframe";
    }

    @PostMapping(value = "/dictionary/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer kind, @RequestParam(required = false) boolean grid) {

        if (kind == null) {
            return DictionaryKinds.toJson();
        }

        if (grid) {
            Page<DictionaryItemInfo> page = new Page<>(); page.rows = dictionaryService.list(kind);
            page.total = page.rows.size(); return page;
        }

        return dictionaryService.list(kind);
        // try {
        //     if (grid) {
        //         Page<DictionaryItemInfo> page = new Page<DictionaryItemInfo>();
        //         page.rows = dictionaryService.list(kind);
        //         page.total = page.rows.size();
        //         return page;
        //     }
        //
        //     return dictionaryService.list(kind);
        // } catch (Exception ex) {
        //     return null;
        // }
    }

    @GetMapping(value = "/dictionary/create.form")
    public String create(@RequestParam int kind, @RequestParam(required = false) Long pid, Model model) {
        DictionaryItem dictionaryItem = new DictionaryItem(); dictionaryItem.setKind(kind); dictionaryItem.setPid(pid);
        model.addAttribute("dictionaryItem", dictionaryItem);

        return "/baseinfo/dictionary/create.form";
    }

    @PostMapping(value = "/dictionary/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("dictionaryItem") @Valid DictionaryItem
                                                                  dictionaryItem, BindingResult binding) {

        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors(); Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/dictionary/create.form";
        dictionaryService.create(dictionaryItem); return ResponseEntityWrapper.OK();
        // try {
        //     dictionaryService.create(dictionaryItem);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/dictionary/edit.form")
    @ResponseBody
    public Object edit(@RequestParam long id, Model model) throws Exception {
        DictionaryItem dictionaryItem = dictionaryService.fetch(id); return dictionaryItem;
        // model.addAttribute("dictionaryItem", dictionaryItem);
        //
        // return "/baseinfo/dictionary/edit.form";
    }

    @PostMapping(value = "/dictionary/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("dictionary") @Valid DictionaryItem
                                                                dictionaryItem, BindingResult binding) {
        if (binding.hasErrors())
            // return "/baseinfo/dictionary/edit.form";
            return ResponseEntityWrapper.Failed(); dictionaryService.update(dictionaryItem);
        return ResponseEntityWrapper.OK();
        // try {
        //     dictionaryService.update(dictionaryItem);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/dictionary/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String ids) {

        List<String> list = Arrays.asList(ids.split(","));
        list.forEach(id -> dictionaryService.delete(Long.valueOf(id)));
        // dictionaryService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     dictionaryService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/dictionary/move")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> move(@RequestParam long id, @RequestParam Long pid) {
        dictionaryService.move(id, pid); return ResponseEntityWrapper.OK();

        // try {
        //     dictionaryService.move(id, pid);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/dictionary/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam int kind, @RequestParam(required = false) Long id,
                         @RequestParam boolean checkId) throws Exception {

        return checkId ? !dictionaryService.exist(name, kind, id) : !dictionaryService.exist(name, kind);
        // if (checkId) {
        //     response.getWriter().print(!dictionaryService.exist(name, kind, id));
        // } else {
        //     response.getWriter().print(!dictionaryService.exist(name, kind));
        // }
    }
}
