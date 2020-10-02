/*
 * Created By: Abhinav Kumar Mishra
 * Copyright &copy; 2020. Abhinav Kumar Mishra. 
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.abhinavmishra14.currconv.feignproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.abhinavmishra14.currconv.model.CurrencyConversionModel;

/**
 * The Interface CurrencyConversionProxy.
 */
@FeignClient(name="currency-exchange-service", url="localhost:8000")
public interface CurrencyConversionProxy {
	
	/**
	 * Gets the exchange rate.<br>
	 * Path variable name needs to be specified when using feign. It does't map the variable name automatically.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the exchange rate
	 */
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<CurrencyConversionModel> getExchangeRate(@PathVariable("from") final String from,
			@PathVariable("to") final String to);
}