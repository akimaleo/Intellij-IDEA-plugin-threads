package com.letit0or1.kawa.plugin.threads.main

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.letit0or1.kawa.plugin.threads.CommentDto
import com.letit0or1.kawa.plugin.threads.ui.ThreadsDialogView
import java.awt.event.MouseEvent

class ThreadsGutterContextAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val lineOfCode = readCurrentLine(e)
        // For example, showing the dialog with comments
        val comments = listOf(
                CommentDto("Alice", "This is a sample comment."),
                CommentDto("Bob", "Another example comment."),
        )

        ThreadsDialogView.showDialog(comments)
    }


    override fun update(e: AnActionEvent) {
        // Set the availability based on some conditions (optional)
        e.presentation.isEnabled = true
    }

    private fun readCurrentLine(e: AnActionEvent): String? {
        val editor = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return null
        val mouseEvent = e.inputEvent as? MouseEvent ?: return null
        val lineNumber = editor.xyToLogicalPosition(mouseEvent.point).line
        val document = editor.document
        val lineStartOffset = document.getLineStartOffset(lineNumber)
        val lineEndOffset = document.getLineEndOffset(lineNumber)
        val lineText = document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))

        // Now you can use the lineText or lineNumber
        println("Line number: $lineNumber, Line text: $lineText")

        return lineText

    }
}
