/* FitCore -- minimal UI JavaScript only. All business logic is server-side. */

// 1. Live form validation feedback
document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('input[type=email]').forEach(inp => {
        inp.addEventListener('blur', () => {
            const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            inp.style.borderColor = re.test(inp.value) || inp.value === '' ? '' : 'var(--danger)';
        });
    });

    // 2. Search filter for already-loaded tables.
    document.querySelectorAll('[data-filter]').forEach(input => {
        const tableId = input.getAttribute('data-filter');
        const rows = document.querySelectorAll('#' + tableId + ' tbody tr');
        input.addEventListener('keyup', () => {
            const q = input.value.toLowerCase();
            rows.forEach(r => r.style.display = r.innerText.toLowerCase().includes(q) ? '' : 'none');
        });
    });

    // 3. Sidebar toggle on mobile.
    const tog = document.getElementById('sidebarToggle');
    if (tog) tog.addEventListener('click', () => {
        document.querySelector('.sidebar').classList.toggle('open');
    });
});

// 4. Wishlist using sessionStorage
function toggleWishlist(sessionId, name) {
    const key = 'wishlist';
    const list = JSON.parse(sessionStorage.getItem(key) || '[]');
    const idx = list.findIndex(x => x.id === sessionId);
    if (idx >= 0) list.splice(idx, 1);
    else list.push({ id: sessionId, name: name });
    sessionStorage.setItem(key, JSON.stringify(list));
    renderWishlistCount();
    const btn = document.querySelector('[data-wishlist="' + sessionId + '"]');
    if (btn) btn.textContent = idx >= 0 ? '☆ Wishlist' : '★ Wishlisted';
}
function renderWishlistCount() {
    const el = document.getElementById('wishlistCount');
    if (!el) return;
    const list = JSON.parse(sessionStorage.getItem('wishlist') || '[]');
    el.textContent = list.length;
}
document.addEventListener('DOMContentLoaded', renderWishlistCount);

// 5. BMI calculator (pure client-side)
function calcBMI() {
    const h = parseFloat(document.getElementById('bmiHeight').value);
    const w = parseFloat(document.getElementById('bmiWeight').value);
    const out = document.getElementById('bmiResult');
    if (!h || !w) { out.textContent = 'Enter height and weight.'; return; }
    const bmi = w / Math.pow(h / 100, 2);
    let cat = '', color = '';
    if (bmi < 18.5)      { cat = 'Underweight'; color = 'var(--warning)'; }
    else if (bmi < 25)   { cat = 'Normal';      color = 'var(--success)'; }
    else if (bmi < 30)   { cat = 'Overweight';  color = 'var(--warning)'; }
    else                 { cat = 'Obese';       color = 'var(--danger)';  }
    out.innerHTML = '<strong style="color:' + color + ';font-size:1.4rem">'
                  + bmi.toFixed(1) + '</strong> -- ' + cat;
}
