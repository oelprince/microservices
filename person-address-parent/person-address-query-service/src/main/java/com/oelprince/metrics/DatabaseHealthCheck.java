package com.oelprince.metrics;
/*
 * The MIT License
 *
 * Copyright 2018 oelprince.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.codahale.metrics.health.HealthCheck;
import com.oelprince.service.PersonAddressQueryService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oelprince
 */
public class DatabaseHealthCheck extends HealthCheck {
    private static final Logger logger = Logger.getLogger(DatabaseHealthCheck.class.getCanonicalName());
    
    public DatabaseHealthCheck(){
        
    }
    
    
    @Override
    protected Result check() throws Exception {
        PersonAddressQueryService addressQueryService = javax.enterprise.inject.spi.CDI.current().select(PersonAddressQueryService.class).get();
        
        try {
            if(addressQueryService.getCount() >= 0) {            
                return Result.healthy();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Database is not healthy.",ex);
        }
        return Result.unhealthy("Database is not healthy.");
    }
    
}
