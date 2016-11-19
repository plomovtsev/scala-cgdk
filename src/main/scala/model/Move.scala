package model

/**
 * Стратегия игрока может управлять волшебником посредством установки свойств объекта данного класса.
 * @param speed Возвращает/задаёт текущую установку скорости перемещения.
 *              Установка скорости перемещения по умолчанию лежит в интервале от { @code -game.wizardBackwardSpeed} до
 *              { @code game.wizardForwardSpeed}, однако границы интервала могут быть расширены в зависимости от изученных
 *              волшебником умений, от действия некоторых аур, а также в случае действия статуса { @code HASTENED}.
 *              <p>
 *              Значения, выходящие за интервал, будут приведены к ближайшей его границе.
 *              Положительные значения соответствуют движению вперёд.
 *              <p>
 *              Если { @code hypot(speed / maxSpeed, strafeSpeed / maxStrafeSpeed)} больше { @code 1.0}, то обе установки скорости
 *              перемещения ({ @code speed} и { @code strafeSpeed}) будут поделены игровым симулятором на это значение.
 * @param strafeSpeed Возвращает/задаёт текущую установку скорости перемещения боком.
 *                    Задаёт установку скорости перемещения боком на один тик.
 *                    <p>
 *                    Установка скорости перемещения по умолчанию лежит в интервале от { @code -game.wizardStrafeSpeed} до
 *                    { @code game.wizardStrafeSpeed}, однако границы интервала могут быть расширены в зависимости от изученных
 *                    волшебником умений, от действия некоторых аур, а также в случае действия статуса { @code HASTENED}.
 *                    <p>
 *                    Значения, выходящие за интервал, будут приведены к ближайшей его границе.
 *                    Положительные значения соответствуют движению направо.
 *                    <p>
 *                    Если { @code hypot(speed / maxSpeed, strafeSpeed / maxStrafeSpeed)} больше { @code 1.0}, то обе установки скорости
 *                    перемещения ({ @code speed} и { @code strafeSpeed}) будут поделены игровым симулятором на это значение.
 * @param turn Возвращает/задаёт текущий угол поворота волшебника.
 *             Устанавливает угол поворота волшебника.
 *             <p/>
 *             Угол поворота задаётся в радианах относительно текущего направления волшебника и обычно ограничен интервалом от
 *             { @code -game.wizardMaxTurnAngle} до { @code game.wizardMaxTurnAngle}. Если на волшебника действует магический
 *             статус { @code HASTENED}, то нижнюю и правую границу интервала необходимо умножить на
 *             { @code 1.0 + game.hastenedRotationBonusFactor}.
 *             <p>
 *             Значения, выходящие за интервал, будут приведены к ближайшей его границе.
 *             Положительные значения соответствуют повороту по часовой стрелке.
 * @param action Возвращает/задаёт текущее действие волшебника.
 *               <p>
 *               Действие может быть проигнорировано игровым симулятором, если у волшебника недостаточно магической энергии для
 *               его совершения и/или волшебник ещё не успел восстановиться после предыдущего действия.
 * @param castAngle Возвращает/задаёт текущий угол полёта магического снаряда.
 *                  Угол полёта задаётся в радианах относительно текущего направления волшебника и ограничен интервалом от
 *                  { @code -game.staffSector / 2.0} до { @code game.staffSector / 2.0}.
 *                  <p>
 *                  Значения, выходящие за интервал, будут приведены к ближайшей его границе.
 *                  Положительные значения соответствуют повороту по часовой стрелке.
 *                  <p>
 *                  Параметр будет проигнорирован игровым симулятором, если действие волшебника не связано с созданием магического
 *                  снаряда.
 * @param minCastDistance Возвращает текущую установку для ближней границы боевого применения магического снаряда.
 *                        Устанавливает ближнюю границу боевого применения магического снаряда.
 *                        <p>
 *                        Если расстояние от центра снаряда до точки его появления меньше, чем значение данного параметра, то боевые
 *                        свойства снаряда игнорируются. Снаряд беспрепятственно проходит сквозь все другие игровые объекты, за исключением
 *                        деревьев.
 *                        <p>
 *                        Значение параметра по умолчанию равно { @code 0.0}. Столкновения снаряда и юнита, который его создал,
 *                        игнорируются.
 *                        <p>
 *                        Параметр будет проигнорирован игровым симулятором, если действие волшебника не связано с созданием магического
 *                        снаряда.
 * @param maxCastDistance Возвращает текущую установку для дальней границы боевого применения магического снаряда.
 *                        Устанавливает дальнюю границу боевого применения магического снаряда.
 *                        <p>
 *                        Если расстояние от центра снаряда до точки его появления больше, чем значение данного параметра, то снаряд
 *                        убирается из игрового мира. При этом, снаряд типа { @code FIREBALL} детонирует.
 *                        <p>
 *                        Значение параметра по умолчанию заведомо выше максимальной дальности полёта любого типа снарядов в игре.
 *                        <p>
 *                        Параметр будет проигнорирован игровым симулятором, если действие волшебника не связано с созданием магического
 *                        снаряда.
 * @param statusTargetId Возвращает идентификатор текущей цели для применения магического статуса.
 *                       Устанавливает идентификатор цели для применения магического статуса.
 *                       <p>
 *                       Допустимыми целями являются только волшебники дружественной фракции. Если волшебник с указанным идентификатором
 *                       не найден, то статус применяется непосредственно к волшебнику, совершающему действие. Относительный угол до цели
 *                       должен лежать в интервале от { @code -game.staffSector / 2.0} до { @code game.staffSector / 2.0}, а максимальная
 *                       дистанция ограничена дальностью полёта магического снаряда этого волшебника. Её базовое значение равно
 *                       { @code game.wizardCastRange}, однако оно может быть увеличено после изучения некоторых умений.
 *                       <p>
 *                       Значение параметра по умолчанию равно { @code -1}.
 *                       <p>
 *                       Параметр будет проигнорирован игровым симулятором, если действие волшебника не связано с применением магического
 *                       статуса.
 * @param skillToLearn Возвращает выбранное для изучения умение.
 *                     Задаёт установку изучить указанное умение до начала следующего игрового тика.
 *                     <p>
 *                     Установка будет проигнорирована игровым симулятором, если текущий уровень волшебника меньше либо равен количеству
 *                     уже изученных умений. Некоторые умения также могут требовать предварительного изучения других умений.
 *                     <p>
 *                     Изучение умений доступно не во всех режимах игры.
 * @param messages Возвращает текущие сообщения для волшебников дружественной фракции.
 *                 Доступно для использования только верховному волшебнику ({ @code wizard.master}). Если используется, количество
 *                 сообщений должно быть строго равно количеству волшебников дружественной фракции (живых или ожидающих возрождения)
 *                 за исключением самого верховного волшебника. Нарушение данных условий может привести к игнорированию параметра
 *                 игровым симулятором или даже к обрыву соединения со стратегией участника.
 *                 <p>
 *                 Сообщения адресуются в порядке возрастания идентификаторов волшебников. Отдельные сообщения могут быть пустыми
 *                 (равны { @code null}), если это поддерживается языком программирования, который использует стратегия. В противном
 *                 случае все элементы должны быть корректными сообщениями.
 *                 <p>
 *                 Игровой симулятор вправе проигнорировать сообщение конкретному волшебнику, если для него в системе уже
 *                 зарегистрировано и ещё им не получено другое сообщение. Если в тик получения сообщения волшебник мёртв, то
 *                 данное сообщение будет удалено из игрового мира и волшебник никогда его не получит.
 *                 <p>
 *                 Отправка сообщений доступна не во всех режимах игры.
 */
class Move(var speed: Double = 0,
           var strafeSpeed: Double = 0,
           var turn: Double = 0,
           var action: ActionType = null,
           var castAngle: Double = 0,
           var minCastDistance: Double = 0,
           var maxCastDistance: Double = 10000.0D,
           var statusTargetId: Long = -1L,
           var skillToLearn: SkillType = null,
           private var _messages: Array[Message] = null) {

    def this() = this(0, 0, 0, null, 0, 0, 10000D, -1L, null, null)

    lazy val messages = if (_messages == null) null else Array() ++ _messages
    def setMessages(msgs: Array[Message]) = {
        _messages = Array() ++ msgs
    }
}