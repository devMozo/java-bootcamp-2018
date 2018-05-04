USE highschool;

DROP PROCEDURE IF EXISTS getPercentageCourse;

DELIMITER //

CREATE PROCEDURE getPercentageCourse(IN _idCourse INT,
                                     OUT _peoplePassed INT)
BEGIN 

    DECLARE idCourse INT;

    SET idCourse = (SELECT id 
                    FROM courses 
                    WHERE id = _idCourse);

    IF(idCourse) THEN
        BEGIN            

            SET _peoplePassed = (SELECT COUNT(id)                                               
                                 FROM final_notes
                                 WHERE  final_notes.id_course = _idCourse AND
                                        (final_notes.note + ( 
                                                            SELECT AVG(partial_notes.note) AS partial_avg_note
                                                            FROM partial_notes
                                                            WHERE partial_notes.id_student = final_notes.id_student    
                                                            )) / 2 >= 4);

            SET _peoplePassed = (_peoplePassed * 100) / (
                                                         SELECT COUNT(id)
                                                         FROM final_notes
                                                         WHERE final_notes.id_course = _idCourse);
        END;
    END IF;

END //

DELIMITER ;

CALL getPercentageCourse(1, @percent);

SELECT @percent AS passed, (100 - @percent) AS failed;

