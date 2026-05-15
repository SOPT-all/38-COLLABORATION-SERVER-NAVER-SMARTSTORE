ALTER TABLE notice
MODIFY COLUMN notice_type ENUM(
    'GENERAL',
    'SYSTEM',
    'DELIVERY',
    'SAFE_TRADE',
    'SELLING_TIP',
    'D_COMMERCE',
    'HAZARD_INFO',
    'MANUAL'
);

UPDATE notice
SET notice_type = 'SYSTEM'
WHERE notice_type = '시스템';