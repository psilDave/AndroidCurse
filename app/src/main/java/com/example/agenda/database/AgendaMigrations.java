package com.example.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {

    static Migration[] TODAS_MIGRATIONS = {new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            MIGRATION_1_2_ADICIONA_CAMPO_SOBRENOME(database);
        }
    }, new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            MIGRATION_2_3_REMOVE_CAMPO_SOBRENOME(database);
        }
    }, new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            MIGRATION_3_4_ADICIONA_CAMPO_MOMENTO_DE_CADASTRO(database);
        }
    }};

    static void MIGRATION_3_4_ADICIONA_CAMPO_MOMENTO_DE_CADASTRO(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE Aluno ADD COLUMN `momentoDeCadasto` INTEGER");
    }

    static void MIGRATION_1_2_ADICIONA_CAMPO_SOBRENOME(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
    }

    static void MIGRATION_2_3_REMOVE_CAMPO_SOBRENOME(@NonNull SupportSQLiteDatabase database) {
        /* Para reverter as alterações em um banco de dados de uma migration, é necessário:
         *  1 - Criar uma nova tabela com as informações desejadas ( o nome precisa ser diferente);
         *  2 - Copiar dos dados da tabela antiga para a nova;
         *  3 - Remover a tabela antiga
         *  4 - Renomear a tabela nova com o nome da antiga
         * */

        database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");

        database.execSQL("INSERT INTO `Aluno_novo`(`ID` , `nome`, `telefone`, `email`) SELECT `ID` , `nome`, `telefone`, `email` FROM Aluno");

        database.execSQL("DROP TABLE `Aluno`");

        database.execSQL("ALTER TABLE `Aluno_novo` RENAME TO `Aluno`");
    }

}
