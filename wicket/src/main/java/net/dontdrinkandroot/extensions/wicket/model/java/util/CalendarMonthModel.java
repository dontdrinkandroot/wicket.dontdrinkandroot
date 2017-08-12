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

import net.dontdrinkandroot.wicket.model.AbstractChainedModel;
import org.apache.wicket.model.IModel;

import java.util.Calendar;

public class CalendarMonthModel extends AbstractChainedModel<Calendar, Integer>
{

    public CalendarMonthModel(IModel<? extends Calendar> parent)
    {
        super(parent);
    }

    @Override
    public Integer getObject()
    {
        return this.getParentObject().get(Calendar.MONTH);
    }

    @Override
    public void setObject(Integer month)
    {
        int oldDay = this.getParentObject().get(Calendar.DAY_OF_MONTH);

        this.getParentObject().set(Calendar.DAY_OF_MONTH, 1);
        this.getParentObject().set(Calendar.MONTH, month);

        int maxDay = this.getParentObject().getActualMaximum(Calendar.DAY_OF_MONTH);

        this.getParentObject().set(Calendar.DAY_OF_MONTH, Math.min(oldDay, maxDay));
    }
}
