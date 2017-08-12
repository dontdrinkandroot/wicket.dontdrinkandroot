/*
 * Copyright (C) 2012-2016 Philip Washington Sorst <philip@sorst.net>
 * and individual contributors as indicated
 * by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dontdrinkandroot.extensions.wicket.model.java.util;

import org.apache.wicket.model.IModel;

import java.util.Calendar;
import java.util.Date;

public class DateMinuteModel extends AbstractDateCalendarModel<Integer>
{

    public DateMinuteModel(IModel<Date> parent)
    {
        super(parent);
    }

    @Override
    protected Integer getFromCalendar(Calendar calendar)
    {
        return calendar.get(Calendar.MINUTE);
    }

    @Override
    protected void setToCalendar(Integer object, Calendar calendar)
    {
        calendar.set(Calendar.MINUTE, object);
    }
}
