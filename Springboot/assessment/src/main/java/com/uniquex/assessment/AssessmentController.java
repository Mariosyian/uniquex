package com.uniquex.assessment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.uniquex.assessment.Algorithms.Algorithms;
import com.uniquex.assessment.Algorithms.BubbleSort;
import com.uniquex.assessment.Algorithms.HeapSort;
import com.uniquex.assessment.Algorithms.MergeSort;

@Controller
public class AssessmentController {
    
    private Context context = new Context();

    @GetMapping(value="/")
    public String getIndex(Model model) {
        model.addAttribute("algorithms", context.getAlgorithms());
        model.addAttribute("students", context.getStudents());
        model.addAttribute("time", context.getTime());
        model.addAttribute("saveSuccess", context.getSaveSuccess());
        model.addAttribute("saveError", context.getSaveError());

        return "index";
    }

    @PostMapping(value="/sort")
    public String postSort(@RequestParam("data") MultipartFile data, @ModelAttribute("sorting") String sorting, Model model) throws IOException {
        
        String stringData = new String(data.getBytes(), StandardCharsets.UTF_8);
        String[] splitData = stringData.split("\n");
        int useableSize = 0;
        for (String s : splitData) {
            if (s.contains(",")) {
                useableSize ++;
            }
        }

        Student[] studentList = new Student[useableSize];
        int counter = 0;
        for (int i = 0; i < splitData.length; i ++) {
            if (splitData[i].contains(",")) {
                String[] split = splitData[i].split(",");
                studentList[counter] = (new Student(split[0], Double.parseDouble(split[1])));
                counter ++;
            }
        }

        if (!Set.of(context.getAlgorithms()).contains(sorting)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        long before = System.nanoTime();
        switch (sorting) {
            case Algorithms.BUBBLE_SORT:
                studentList = new BubbleSort().sortAndReturn(studentList);
                break;
            case Algorithms.HEAP_SORT:
                studentList = new HeapSort().sortAndReturn(studentList);
                break;
            case Algorithms.MERGE_SORT:
                studentList = new MergeSort().sortAndReturn(studentList, 0, studentList.length);
                break;
            default:
                break;
        }
        long after = System.nanoTime();

        model.addAttribute("algorithms", context.getAlgorithms());
        model.addAttribute("students", studentList);
        model.addAttribute("time", (after - before) / 1000000000);
        model.addAttribute("saveSuccess", context.getSaveSuccess());
        model.addAttribute("saveError", context.getSaveError());

        return "index";
    }
}
