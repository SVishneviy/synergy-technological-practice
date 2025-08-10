const input = document.getElementById('dateInput');
    const btn   = document.getElementById('calcBtn');
    const daysOut = document.getElementById('daysOut');
    const leapOut = document.getElementById('leapOut');
    const errorBox = document.getElementById('error');

    const MS_PER_DAY = 24 * 60 * 60 * 1000;

    // Маска
    input.addEventListener('input', (e) => {
      let v = e.target.value.replace(/[^\d]/g, '').slice(0, 8); // только цифры
      if (v.length >= 5) v = v.replace(/^(\d{2})(\d{2})(\d{1,4}).*$/, '$1.$2.$3');
      else if (v.length >= 3) v = v.replace(/^(\d{2})(\d{1,2}).*$/, '$1.$2');
      e.target.value = v;
    });

    btn.addEventListener('click', () => {
      clearError();
      const raw = input.value.trim();

      const parsed = parseDdMmYyyy(raw);
      if (!parsed.ok) {
        showError(parsed.message);
        setOutputs('—', '—');
        return;
      }
      const { date, day, month, year } = parsed;

      const days = daysUntilNY(date);
      const leap = isLeapYear(year) ? 'високосный' : 'не високосный';

      setOutputs(days.toString(), leap, isLeapYear(year));
    });

    function setOutputs(days, leapText, leapBool) {
      daysOut.textContent = days;
      leapOut.textContent = leapText;
      leapOut.classList.toggle('ok', !!leapBool);
      leapOut.classList.toggle('bad', !leapBool && leapText !== '—');
    }

    function showError(msg) {
      errorBox.style.display = '';
      errorBox.textContent = msg;
    }
    function clearError() {
      errorBox.style.display = 'none';
      errorBox.textContent = '';
    }

    // Парсинг dd.mm.yyyy
    function parseDdMmYyyy(str) {
      const m = /^(\d{2})\.(\d{2})\.(\d{4})$/.exec(str);
      if (!m) return { ok: false, message: 'Неверный формат. Используйте дд.мм.гггг.' };

      const day = Number(m[1]);
      const month = Number(m[2]);
      const year = Number(m[3]);

      if (month < 1 || month > 12) return { ok: false, message: 'Месяц должен быть в диапазоне 01–12.' };
      if (day < 1) return { ok: false, message: 'День должен быть 01 или больше.' };

      const maxDay = daysInMonth(year, month);
      if (day > maxDay) return { ok: false, message: `В ${month}.${year} только ${maxDay} дней.` };

      // Создаём дату в полдень, чтобы избежать проблем с переводом часов/часовыми поясами
      const date = new Date(year, month - 1, day, 12, 0, 0, 0);
      return { ok: true, date, day, month, year };
    }

    function daysInMonth(year, month) {
      return new Date(year, month, 0).getDate();
    }

    function isLeapYear(year) {
      return (year % 4 === 0) && (year % 100 !== 0 || year % 400 === 0);
    }

    function daysUntilNY(fromDate) {
      const target = new Date(fromDate.getFullYear(), 11, 31, 12, 0, 0, 0);
      const diffMs = target - fromDate;
      // Если дата позже 31.12
      if (diffMs < 0) return 0;
      // Чистое число дней
      return Math.floor(diffMs / MS_PER_DAY);
    }