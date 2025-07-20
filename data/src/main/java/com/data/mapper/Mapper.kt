package com.data.mapper

import com.domain.model.NoteModel
import com.data.model.NoteEntity

fun NoteEntity.toDomainModel(): NoteModel {
    return NoteModel(
        id = id,
        title = title,
        content = content,
    )
}

fun NoteModel.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
    )
}