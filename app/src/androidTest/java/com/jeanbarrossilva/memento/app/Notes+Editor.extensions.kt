package com.jeanbarrossilva.memento.app

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.jeanbarrossilva.aurelius.ui.layout.dialog.ConfirmationButton
import com.jeanbarrossilva.aurelius.ui.layout.dialog.DIALOG_CONFIRMATION_BUTTON_TAG
import com.jeanbarrossilva.aurelius.ui.layout.dialog.Dialog
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FLOATING_ACTION_BUTTON_TAG
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.DeleteAction
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.memento.app.fixtures.toNoteColors
import com.jeanbarrossilva.memento.feature.editor.ui.input.notebody.NOTE_BODY_TAG
import com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.NOTE_HEADLINE_TITLE_TAG
import com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.colors.noteHeadlineColorBubbleTagFor
import com.jeanbarrossilva.memento.feature.editor.ui.layout.scaffold.topappbar.TOP_APP_BAR_NAVIGATION_BUTTON_TAG
import com.jeanbarrossilva.memento.feature.notes.domain.note.Gradient
import com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.topappbar.TOP_APP_BAR_DELETE_ACTION_TAG

/** [SemanticsNodeInteraction] of [TopAppBar]'s navigation button. **/
internal fun ComposeTestRule.onNavigationButton(): SemanticsNodeInteraction {
    return onNodeWithTag(TOP_APP_BAR_NAVIGATION_BUTTON_TAG)
}

/** [SemanticsNodeInteraction] of [Dialog]'s [ConfirmationButton]. **/
internal fun ComposeTestRule.onConfirmationButton(): SemanticsNodeInteraction {
    return onNodeWithTag(DIALOG_CONFIRMATION_BUTTON_TAG)
}

/** [SemanticsNodeInteraction] of the [FloatingActionButton]. **/
internal fun ComposeTestRule.onFab(): SemanticsNodeInteraction {
    return onNodeWithTag(FLOATING_ACTION_BUTTON_TAG)
}

/**
 * [SemanticsNodeInteraction] of the given [gradient]'s note headline color bubble.
 *
 * @param gradient [Gradient] whose color bubble's [SemanticsNodeInteraction] will be returned.
 **/
internal fun ComposeTestRule.onNoteHeadlineColorBubble(gradient: Gradient):
    SemanticsNodeInteraction {
    val colors = gradient.toNoteColors()
    val tag = noteHeadlineColorBubbleTagFor(colors)
    return onNodeWithTag(tag)
}

/** [SemanticsNodeInteraction] of the note body. **/
internal fun ComposeTestRule.onNoteBody(): SemanticsNodeInteraction {
    return onNodeWithTag(NOTE_BODY_TAG)
}

/** [SemanticsNodeInteraction] of the note headline title. **/
internal fun ComposeTestRule.onNoteHeadlineTitle(): SemanticsNodeInteraction {
    return onNodeWithTag(NOTE_HEADLINE_TITLE_TAG)
}

/** [SemanticsNodeInteraction] of [TopAppBar]'s [DeleteAction]. **/
internal fun ComposeTestRule.onDeleteAction(): SemanticsNodeInteraction {
    return onNodeWithTag(TOP_APP_BAR_DELETE_ACTION_TAG)
}
