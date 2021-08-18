/*
 * Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dao.entity.Kayak;
import com.example.demo.dao.repository.KayakRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KayakService {

	private final KayakRepository kayakRepository ;

	public KayakService(final KayakRepository kayakRepository) {
		this.kayakRepository = kayakRepository ;
	}

	@Transactional
	public Kayak createKayak(final String type,final String modelCode, final String brandName) {
		final Kayak kayak = new Kayak();
		kayak.setType(type);
		kayak.setModelCode(modelCode);
		kayak.setBrandName(brandName);
		return this.kayakRepository.save(kayak);
	}

	@Transactional(readOnly = true)
	public List<Kayak> getAllKayaks(final int count) {
		return this.kayakRepository.findAll().stream().limit(count).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Optional<Kayak> getKayak(final int id) {
		return this.kayakRepository.findById(id);
	}
}
