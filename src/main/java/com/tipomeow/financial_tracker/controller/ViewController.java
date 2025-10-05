package com.tipomeow.financial_tracker.controller;

import com.tipomeow.financial_tracker.dto.CategoryDto;
import com.tipomeow.financial_tracker.dto.CategoryRequestDto;
import com.tipomeow.financial_tracker.dto.TransactionDto;
import com.tipomeow.financial_tracker.dto.TransactionRequestDto;
import com.tipomeow.financial_tracker.entity.CategoryType;
import com.tipomeow.financial_tracker.services.CategoryService;
import com.tipomeow.financial_tracker.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ViewController {

    private final TransactionService transactionService;
    private final CategoryService categoryService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Instant.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    setValue(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                }
            }

            @Override
            public String getAsText() {
                Instant instant = (Instant) getValue();
                if (instant == null) {
                    return "";
                }
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        });
    }

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        model.addAttribute("summary", transactionService.getCategoryExpenseSummary());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("newTransaction", new TransactionRequestDto());
        return "index";
    }

    @PostMapping("/transactions/add")
    public String addTransaction(@ModelAttribute TransactionRequestDto newTransaction, RedirectAttributes redirectAttributes) {
        transactionService.createTransaction(newTransaction);
        redirectAttributes.addFlashAttribute("successMessage", "Transaction added successfully!");
        return "redirect:/";
    }

    @GetMapping("/transactions/edit/{id}")
    public String showEditTransactionForm(@PathVariable Long id, Model model) {
        TransactionDto transaction = transactionService.getTransactionById(id);

        TransactionRequestDto requestDto = new TransactionRequestDto();
        requestDto.setDescription(transaction.getDescription());
        requestDto.setAmount(transaction.getAmount());
        requestDto.setCategoryId(transaction.getCategoryId());
        requestDto.setTime(transaction.getTime());

        model.addAttribute("transactionId", id);
        model.addAttribute("transactionRequest", requestDto);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-transaction";
    }

    @PostMapping("/transactions/update/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute TransactionRequestDto transactionRequest, RedirectAttributes redirectAttributes) {
        transactionService.updateTransaction(id, transactionRequest);
        redirectAttributes.addFlashAttribute("successMessage", "Transaction updated successfully!");
        return "redirect:/";
    }

    @GetMapping("/transactions/delete/{id}")
    public String deleteTransaction(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        transactionService.deleteTransaction(id);
        redirectAttributes.addFlashAttribute("successMessage", "Transaction deleted successfully!");
        return "redirect:/";
    }

    @GetMapping("/categories")
    public String viewCategoriesPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("newCategory", new CategoryRequestDto());
        return "categories";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute CategoryRequestDto newCategory, RedirectAttributes redirectAttributes) {
        try {
            categoryService.createCategory(newCategory);
            redirectAttributes.addFlashAttribute("successMessage", "Category added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        CategoryDto category = categoryService.getCategoryById(id);

        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName(category.getName());
        requestDto.setType(category.getType());

        model.addAttribute("categoryId", id);
        model.addAttribute("categoryRequest", requestDto);
        return "edit-category";
    }

    @PostMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryRequestDto categoryRequest, RedirectAttributes redirectAttributes) {
        try {
            categoryService.updateCategory(id, categoryRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }
}
