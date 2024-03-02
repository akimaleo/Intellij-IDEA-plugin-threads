package com.letit0or1.kawa.plugin.threads.ui

import com.letit0or1.kawa.plugin.threads.CommentDto
import java.awt.*
import javax.swing.*

class ThreadsDialogView : JDialog() {
    private val contentPane = JPanel(BorderLayout())
    private val sendButton = JButton("Send")
    private val inputField = JTextField()
    private val listModel = DefaultListModel<CommentDto>()
    private val commentList = JList(listModel)

    init {
        title = "Comments"
        setContentPane(contentPane)
        isModal = true

        commentList.cellRenderer = CommentListCellRenderer()
        contentPane.add(JScrollPane(commentList), BorderLayout.CENTER)

        val bottomPanel = JPanel(BorderLayout())
        bottomPanel.add(inputField, BorderLayout.CENTER)
        bottomPanel.add(sendButton, BorderLayout.EAST)
        contentPane.add(bottomPanel, BorderLayout.SOUTH)

        sendButton.addActionListener {
            // Logic to handle send action
            dispose()
        }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(400, 400)
    }

    private class CommentListCellRenderer : JPanel(BorderLayout()), ListCellRenderer<CommentDto> {
        private val nameIconLabel = JLabel()
        private val nameLabel = JLabel()
        private val commentLabel = JLabel()

        init {
            val iconPanel = CirclePanel()
            iconPanel.add(nameIconLabel)
            this.add(iconPanel, BorderLayout.WEST)

            val namePanel = JPanel(BorderLayout())
            namePanel.add(nameLabel, BorderLayout.CENTER)

            this.add(namePanel, BorderLayout.WEST)

            val commentPanel = JPanel(BorderLayout())
            commentPanel.add(commentLabel, BorderLayout.CENTER)

            this.add(commentPanel, BorderLayout.CENTER)
        }

        override fun getListCellRendererComponent(
                list: JList<out CommentDto>?,
                value: CommentDto?,
                index: Int,
                isSelected: Boolean,
                cellHasFocus: Boolean
        ): Component {
            value?.let {
                nameIconLabel.text = value.name.first().uppercase()
                nameLabel.text = value.name
                commentLabel.text = value.comment
            }

            if (isSelected) {
                background = list?.selectionBackground
                foreground = list?.selectionForeground
            } else {
                background = list?.background
                foreground = list?.foreground
            }
            return this
        }
    }

    companion object {
        fun showDialog(lineOfCode: String, comments: List<CommentDto>) {
            val dialog = ThreadsDialogView()
            comments.forEach { dialog.listModel.addElement(it) }
            dialog.isVisible = true
        }
    }
}

// For testing
fun main() {
    val comments = listOf(
            CommentDto("Alice", "This is a sample comment."),
            CommentDto("Bob", "Another example comment."),
    )

    SwingUtilities.invokeLater { ThreadsDialogView.showDialog(comments) }
}
